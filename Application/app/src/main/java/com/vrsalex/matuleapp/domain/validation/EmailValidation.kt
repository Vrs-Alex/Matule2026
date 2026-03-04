package com.vrsalex.matuleapp.domain.validation

import android.util.Patterns

object EmailValidation {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$".toRegex()

    fun validateEmail(email: String): String? {
//        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isValid = emailRegex.matches(email)
        if (!isValid) {
            return "Некорректный формат почты"
        }
        return null
    }

}