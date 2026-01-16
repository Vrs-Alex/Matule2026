package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTokensResponse(
    val accessToken: String,
    val refreshToken: String
)
