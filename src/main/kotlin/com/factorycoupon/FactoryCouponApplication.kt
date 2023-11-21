package com.factorycoupon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class FactoryCouponApplication

fun main(args: Array<String>) {
    runApplication<FactoryCouponApplication>(*args)
}
