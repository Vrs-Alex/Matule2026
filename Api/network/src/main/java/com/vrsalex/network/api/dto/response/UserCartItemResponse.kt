package com.vrsalex.network.api.dto.response

import kotlinx.serialization.Serializable
import com.vrsalex.network.api.dto.response.ProductResponse

@Serializable
data class UserCartItemResponse(
    val cartId: Long,
    val product: ProductResponse,
    val quantity: Int
)