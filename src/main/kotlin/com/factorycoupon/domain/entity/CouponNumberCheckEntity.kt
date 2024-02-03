package com.factorycoupon.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    name = "COUPON_CHECK",
    indexes = [Index(name = "idx_coupon_check_number", columnList = "coupon_number")]
)
class CouponNumberCheckEntity(

    @Column(name = "COUPON_NUMBER", nullable = false)
    var couponNumber: String,

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
