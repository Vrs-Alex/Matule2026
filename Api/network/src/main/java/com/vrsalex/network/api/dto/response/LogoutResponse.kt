package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(
    val success: Boolean
)
