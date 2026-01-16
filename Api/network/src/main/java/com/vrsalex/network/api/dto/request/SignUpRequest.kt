package com.vrsalex.network.api.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String
)
