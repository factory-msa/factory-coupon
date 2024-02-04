package com.factorycoupon.api.v1

import com.factorycoupon.api.dto.ApiResponse
import com.factorycoupon.application.service.CouponSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponSearchController(
    private val couponSearchService: CouponSearchService
) {

    @GetMapping("/api/v1/coupons/{couponNumber}")
    fun getCoupon(
        @PathVariable couponNumber: String
    ): ApiResponse<CouponSearchService.CouponSearchApiResponse> {
        return ApiResponse.ok(
            couponSearchService.get(couponNumber)
        )
    }

}
