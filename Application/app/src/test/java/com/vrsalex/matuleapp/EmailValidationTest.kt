package com.vrsalex.matuleapp

import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.domain.validation.EmailValidation
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EmailValidationTest {


    @Test
    fun `email validation should return null for valid email`() {

        // Arrange
        val email = "alex@gmail.com"

        // Act
        val result = EmailValidation.validateEmail(email)

        // Assert
        assertThat(result).isNull()
    }


    @ParameterizedTest
    @ValueSource(strings = [
        "alexgmail.com",
        "alex%@gmail.com",
        "",
        "alex@gmail",
        "@gmail.com",
    ])
    fun `email validation should return error message for invalid email`(invalidEmail: String) {
        // Act
        val result = EmailValidation.validateEmail(invalidEmail)

        // Assert
        assertThat(result).isEqualTo("Некорректный формат почты")
    }


}