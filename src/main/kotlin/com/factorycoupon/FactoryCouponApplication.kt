package com.factorycoupon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class FactoryCouponApplication

fun main(args: Array<String>) {
    runApplication<FactoryCouponApplication>(*args)
}
