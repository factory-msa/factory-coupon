package com.factorycoupon.application.support

import kotlin.random.Random

/** https://github.com/voucherifyio/voucher-code-generator-java */
class VoucherCodes {

    companion object {
        private val RANDOM: Random = Random(System.currentTimeMillis())

        /**
         * Generates a random code according to given config.
         *
         * @param config
         * @return Generated code.
         */
        fun generate(config: CodeConfig): String {
            val sb = StringBuilder()
            val chars: CharArray = config.charset.toCharArray()

            config.prefix.let { sb.append(it) }

            repeat(config.length) {
                sb.append(chars[RANDOM.nextInt(chars.size)])
            }

            return sb.toString()
        }
    }

}
