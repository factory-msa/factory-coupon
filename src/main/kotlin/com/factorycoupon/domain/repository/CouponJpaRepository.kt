package com.factorycoupon.domain.repository

import com.factorycoupon.domain.entity.CouponEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponJpaRepository: JpaRepository<CouponEntity, String> {
}
