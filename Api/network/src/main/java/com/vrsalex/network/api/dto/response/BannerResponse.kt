package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val id: Long,
    val title: String,
    val image: String
)