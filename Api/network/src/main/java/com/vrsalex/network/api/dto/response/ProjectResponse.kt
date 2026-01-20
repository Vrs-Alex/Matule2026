package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
    val id: Long,
    val name: String,
    val startDate: String,
    val endDate: String,
    val url: String,
    val type: String,
    val category: String,
    val createdAt: String,
    val updatedAt: String
)