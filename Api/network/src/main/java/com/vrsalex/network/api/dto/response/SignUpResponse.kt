package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val accessToken: String,
    val refreshToken: String
)
