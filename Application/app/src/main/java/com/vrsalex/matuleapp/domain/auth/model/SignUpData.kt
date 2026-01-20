package com.vrsalex.matuleapp.domain.auth.model

data class SignUpData(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val patronymic: String = "",
    val birthday: String = "",
    val gender: String = ""
)
