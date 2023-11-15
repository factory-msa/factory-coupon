package com.factorycoupon.domain.entity

import support.domain.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "COUPON")
class CouponEntity(

    @Id
    @Column(name = "COUPON_NUMBER", nullable = false)
    var couponNumber: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "COUPON_STATUS_CODE", nullable = false)
    var couponStatusCode: CouponStatusCode,

    @Column(name = "EXPIRED_FROM", nullable = false)
    var expiredFrom: LocalDateTime,

    @Column(name = "EXPIRED_TO", nullable = false)
    var expiredTo: LocalDateTime,

    @Column(name = "ISSUANCE_ID", nullable = false)
    var issuanceId: String,

    @Column(name = "PRODUCT_ID")
    var productId: String,

    @Column(name = "PRICE", nullable = false)
    var price: Long
): BaseTimeEntity() {

    @Column(name = "DELETED", nullable = false)
    private var deleted: Boolean = false

}
