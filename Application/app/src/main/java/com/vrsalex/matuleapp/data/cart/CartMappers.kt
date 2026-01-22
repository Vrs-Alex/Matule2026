package com.vrsalex.matuleapp.data.cart

import com.vrsalex.matuleapp.data.local.db.entity.CartItemEntity
import com.vrsalex.matuleapp.data.local.db.relation.CartItemWithProductEntity
import com.vrsalex.matuleapp.data.product.toDomain
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.network.api.dto.response.UserCartItemResponse

fun UserCartItemResponse.toEntity() = CartItemEntity(
    productId = this.product.id,
    quantity = this.quantity
)


fun CartItem.toEntity() = CartItemEntity(
    productId = this.product.id,
    quantity = this.quantity
)