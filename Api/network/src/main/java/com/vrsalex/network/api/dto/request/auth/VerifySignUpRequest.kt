package com.vrsalex.network.api.dto.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class VerifySignUpRequest(
    val phoneNumber: String,
    val code: Int
)
