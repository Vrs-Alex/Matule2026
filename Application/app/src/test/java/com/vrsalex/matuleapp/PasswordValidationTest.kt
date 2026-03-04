package com.vrsalex.matuleapp

import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.domain.validation.PasswordValidation
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasswordValidationTest {

    @Nested
    inner class SuccessTests {
        @ParameterizedTest
        @ValueSource(strings = ["Valid123!", "P@ssword1", "Admin_2026", "Secure*789"])
        fun `valid passwords should return null`(password: String) {
            val result = PasswordValidation.validatePassword(password)
            assertThat(result).isNull()
        }
    }

    @Nested
    inner class LengthAndEmptyTests {
        @Test
        fun `empty password should return specific error`() {
            val result = PasswordValidation.validatePassword("")
            assertThat(result).isEqualTo("Пароль не может быть пустым")
        }

        @ParameterizedTest
        @ValueSource(strings = ["123", "Ab1!", "Short1"])
        fun `short passwords should return length error`(password: String) {
            val result = PasswordValidation.validatePassword(password)
            assertThat(result).isEqualTo("Пароль должен содержать не менее 8 символов")
        }
    }

    @Nested
    inner class ComplexityTests {
        @ParameterizedTest
        @ValueSource(strings = [
            "password123!", // нет заглавной
            "PASSWORD123!", // нет строчной
            "Password!!!",  // нет цифры
            "Password123"   // нет спецсимвола
        ])
        fun `passwords lacking complexity should return complexity error`(password: String) {
            val result = PasswordValidation.validatePassword(password)
            assertThat(result).isEqualTo("Пароль должен содержать заглавные и строчные буквы, цифры и спецсимволы")
        }
    }
}