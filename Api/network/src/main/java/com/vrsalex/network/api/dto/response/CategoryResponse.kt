package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: Long,
    val name: String,
    val description: String
)