package com.factorycoupon.domain.repository

import com.factorycoupon.domain.entity.CouponNumberCheckEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CouponCheckJpaRepository : JpaRepository<CouponNumberCheckEntity, String> {

    fun existsByCouponNumber(couponNumber: String): Boolean

}
