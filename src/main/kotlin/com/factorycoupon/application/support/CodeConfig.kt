package com.factorycoupon.application.support

import com.factorycoupon.application.support.CodeConfig.Charset.ALPHANUMERIC

class CodeConfig(
    val length: Int = DEFAULT_LENGTH,
    val charset: String = ALPHANUMERIC,
    val prefix: String = ""
) {
    object Charset {
        const val ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val NUMBERS = "0123456789"
    }

    companion object {
        const val DEFAULT_LENGTH = 12
    }
}
