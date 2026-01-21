package com.vrsalex.matuleapp.domain.profile

data class FullProfile(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val email: String,
    val birthday: String,
    val gender: String
)
