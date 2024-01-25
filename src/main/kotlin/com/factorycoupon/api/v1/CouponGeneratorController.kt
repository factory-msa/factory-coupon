package com.factorycoupon.api.v1

import com.factorycoupon.api.dto.ApiResponse
import com.factorycoupon.application.service.CouponGeneratorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponGeneratorController(
    private val service: CouponGeneratorService
) {

    @PostMapping("/api/v1/coupons/issue")
    fun generateCoupons(
        @RequestBody command: CouponGeneratorService.CouponIssuanceApiRequest
    ): ApiResponse<CouponGeneratorService.CouponIssuanceApiResponse> {

        return ApiResponse.ok(
            service.generateCoupons(command)
        )
    }

}
