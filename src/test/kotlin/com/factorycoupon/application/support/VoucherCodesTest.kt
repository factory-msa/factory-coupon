package com.factorycoupon.application.support

import com.factorycoupon.application.support.CodeConfig.Charset.NUMBERS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("코드 랜덤 생성기 테스트")
class VoucherCodesTest {

    @Test
    fun `기본적으로 길이가 8인 코드가 생성된다`() {
        val config = CodeConfig()

        val code = VoucherCodes.generate(config)

        assertThat(code.length).isEqualTo(8)
    }

    @ParameterizedTest
    @ValueSource(ints = [8, 10, 12])
    fun `길이를 설정한 Config 로 코드를 생성하면 해당 길이의 랜덤 코드가 생성된다`(length: Int) {
        val config = CodeConfig(length = length)

        val code = VoucherCodes.generate(config)

        assertThat(code.length).isEqualTo(length)
    }

    @RepeatedTest(value = 5)
    fun `Number Charset 을 설정한 Config 로 코드를 생성하면 숫자로만 이루어진 랜덤 코드가 생성된다`() {
        val config = CodeConfig(charset = NUMBERS)

        val code = VoucherCodes.generate(config)

        assertTrue(code.matches(Regex("[0-9]+")))
    }

    @ParameterizedTest
    @ValueSource(strings = ["02", "999", "087"])
    fun `prefix 를 설정한 Config 로 코드를 생성하면 prefix 로 시작하는 랜덤 코드가 생성된다`(prefix: String) {
        val config = CodeConfig(prefix = prefix)

        val code = VoucherCodes.generate(config)

        assertTrue(code.startsWith(prefix))
    }
}
