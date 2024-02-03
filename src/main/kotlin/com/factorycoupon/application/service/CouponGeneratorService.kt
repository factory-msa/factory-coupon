package com.factorycoupon.application.service

import com.factorycoupon.application.support.CodeConfig
import com.factorycoupon.application.support.VoucherCodes
import com.factorycoupon.domain.entity.CouponEntity
import com.factorycoupon.domain.entity.CouponNumberCheckEntity
import com.factorycoupon.domain.entity.CouponStatusCode
import com.factorycoupon.domain.repository.CouponCheckJpaRepository
import com.factorycoupon.domain.repository.CouponJpaRepository
import org.springframework.stereotype.Service
import support.logging.logger
import java.time.LocalDateTime

@Service
class CouponGeneratorService(
    private val couponRepository: CouponJpaRepository,
    private val couponCheckRepository: CouponCheckJpaRepository
) {

    private val logger = logger()

    fun generateCoupons(command: CouponIssuanceApiRequest): CouponIssuanceApiResponse {
        val startTime = System.currentTimeMillis()
        val coupons = mutableListOf<CouponEntity>()
        val checkCoupons = mutableListOf<CouponNumberCheckEntity>()

        while (isGeneratingCoupons(coupons.size, command.issuanceQuantity)) {
            val couponNumber = VoucherCodes.generate(codeConfig)

            if (couponCheckRepository.existsByCouponNumber(couponNumber)) {
                logger.info("duplicate couponNumber: `{}`", couponNumber)
                continue
            }

            val coupon = CouponEntity(
                couponNumber,
                CouponStatusCode.CPN001,
                command.expiredFrom,
                command.expiredTo,
                command.issuanceId,
                command.productId,
                command.price
            )

            coupons.add(coupon)
            checkCoupons.add(CouponNumberCheckEntity(couponNumber))
        }

        couponRepository.saveAll(coupons)
        couponCheckRepository.saveAll(checkCoupons)

        logger.debug("쿠폰 생성이 완료되었습니다. 발급ID: `${command.issuanceId}`, 생성수량: `${command.issuanceQuantity}`")

        val endTime = System.currentTimeMillis()
        val executedTime = endTime - startTime

        logger.debug("쿠폰 생성에 실행된 시간: {}", executedTime)

        return CouponIssuanceApiResponse(
            coupons.map { it.couponNumber }.toMutableList()
        )
    }

    private fun isGeneratingCoupons(
        size: Int,
        issuanceQuantity: Int
    ) = size < issuanceQuantity

    class CouponIssuanceApiRequest(
        val issuanceId: String,
        val productId: String,
        val issuanceQuantity: Int,
        val price: Long,
        val expiredFrom: LocalDateTime,
        val expiredTo: LocalDateTime,
    )

    class CouponIssuanceApiResponse(
        val couponNumbers: MutableList<String>
    )

    companion object {
        private val codeConfig = CodeConfig()
    }

}
