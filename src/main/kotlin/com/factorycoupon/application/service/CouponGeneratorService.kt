package com.factorycoupon.application.service

import com.factorycoupon.application.support.CodeConfig
import com.factorycoupon.application.support.VoucherCodes
import com.factorycoupon.domain.entity.CouponEntity
import com.factorycoupon.domain.entity.CouponStatusCode
import com.factorycoupon.domain.repository.CouponJpaRepository
import org.springframework.stereotype.Service
import support.logging.logger
import java.time.LocalDateTime

@Service
class CouponGeneratorService(
    private val repository: CouponJpaRepository
) {

    private val logger = logger()

    /**
     * 발급 수량 만큼 반복하면서 쿠폰 생성
     *  TODO: 쿠폰 생성 실패 케이스 고려 (쿠폰 생성 시간 초과, retry 로직 추가 후 retry 초과, 네트워크 오류 등), 쿠폰 번호 중복 생성 로깅 (횟수 카운트 위해)
     *
     */
    fun generateCoupons(command: CouponIssuanceApiRequest): CouponIssuanceApiResponse {
        val couponNumbers = mutableListOf<String>()

        while (couponNumbers.size != command.issuanceQuantity) {
            val couponNumber = VoucherCodes.generate(codeConfig)

            val coupon = CouponEntity(
                couponNumber,
                CouponStatusCode.CPN001,
                command.expiredFrom,
                command.expiredTo,
                command.issuanceId,
                command.productId,
                command.price
            )

            // TODO: Coupon save vs Coupons saveAll 성능 비교
            repository.save(coupon)
            // repository.saveAll(coupons)

            couponNumbers.add(couponNumber)
        }

        return CouponIssuanceApiResponse(
            couponNumbers
        )
    }

    class CouponIssuanceApiRequest(
        val issuanceId: String,
        val productId: String,
        val issuanceQuantity: Int,
        val price: Long,
        val expiredFrom: LocalDateTime,
        val expiredTo: LocalDateTime,
    )

    class CouponIssuanceApiResponse(
        val couponNumbers: List<String>
    )

    companion object {
        private val codeConfig = CodeConfig()
    }

}
