package com.factorycoupon.application.service

import com.factorycoupon.domain.entity.CouponEntity
import com.factorycoupon.domain.repository.CouponJpaRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CouponSearchService(
    private val repository: CouponJpaRepository
) {

    fun get(couponNumber: String): CouponSearchApiResponse {
        val coupon = repository.findById(couponNumber)
            .orElseThrow { IllegalStateException("$couponNumber not exist!") }

        return CouponSearchApiResponse.fromEntity(coupon)
    }

    class CouponSearchApiResponse(
        val couponNumber: String,
        val couponStatusCode: String,
        val expiredFrom: LocalDateTime,
        val expiredTo: LocalDateTime,
        val issuanceId: String,
        val productId: String,
        val price: Long
    ) {
        companion object {
            fun fromEntity(coupon: CouponEntity): CouponSearchApiResponse {
                return CouponSearchApiResponse(
                    coupon.couponNumber,
                    coupon.couponStatusCode.name,
                    coupon.expiredFrom,
                    coupon.expiredTo,
                    coupon.issuanceId,
                    coupon.productId,
                    coupon.price,
                )
            }
        }
    }
}
