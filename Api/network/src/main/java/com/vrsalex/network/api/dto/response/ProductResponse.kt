package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val id: Long,
    val name: String,
    val subtitle: String,
    val price: String,
    val description: String,
    val category: CategoryResponse
)