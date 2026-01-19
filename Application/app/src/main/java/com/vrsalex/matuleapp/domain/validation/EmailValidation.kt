package com.vrsalex.matuleapp.domain.validation

import android.util.Patterns

object EmailValidation {

    fun validateEmail(email: String): String? {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isValid) {
            return "Некорректный формат почты"
        }
        return null
    }

}