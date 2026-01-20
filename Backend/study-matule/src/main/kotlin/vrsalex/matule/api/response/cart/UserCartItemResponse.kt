package vrsalex.matule.api.response.cart

import kotlinx.serialization.Serializable
import vrsalex.matule.api.response.product.ProductResponse

@Serializable
data class UserCartItemResponse(
    val cartId: Long,
    val product: ProductResponse,
    val quantity: Int
)
