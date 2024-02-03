package com.factorycoupon.application.support

import com.factorycoupon.application.support.CodeConfig.Charset.NUMBERS

class CodeConfig(
    val length: Int = DEFAULT_LENGTH,
    val charset: String = NUMBERS,
    val prefix: String = ""
) {
    object Charset {
        const val ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val NUMBERS = "0123456789"
    }

    companion object {
        const val DEFAULT_LENGTH = 12
//        const val DEFAULT_LENGTH = 9
    }
}
