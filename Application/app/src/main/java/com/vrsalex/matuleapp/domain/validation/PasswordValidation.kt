package com.vrsalex.matuleapp.domain.validation

object PasswordValidation {

    val regex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@!?*&_-])[A-Za-z\\d@!?*&_-]+$")

    fun validatePassword(password: String): String? {
        if (password.isEmpty()) {
            return "Пароль не может быть пустым"
        }
        if (password.length < 8) {
            return "Пароль должен содержать не менее 8 символов"
        }
        else {
            return if (regex.matches(password)) null
            else "Пароль должен содержать заглавные и строчные буквы, цифры и спецсимволы"
        }
    }

}