package vrsalex.matule.domain.model

import java.time.LocalDate

data class User(
    val id: Long? = null,
    val email: String,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String,
    val verified: Boolean,
    val phoneNum: String
)
