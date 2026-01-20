package vrsalex.matule.api.response.category

import kotlinx.serialization.Serializable
import vrsalex.matule.domain.model.Category

@Serializable
data class CategoryResponse(
    val id: Long,
    val name: String,
    val description: String
)