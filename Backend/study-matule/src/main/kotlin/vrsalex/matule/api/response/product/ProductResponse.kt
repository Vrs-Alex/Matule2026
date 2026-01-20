package vrsalex.matule.api.response.product

import kotlinx.serialization.Serializable
import vrsalex.matule.api.response.category.CategoryResponse
import vrsalex.matule.domain.model.Product

@Serializable
data class ProductResponse(
    val id: Long,
    val name: String,
    val subtitle: String,
    val price: String,
    val description: String,
    val category: CategoryResponse
)