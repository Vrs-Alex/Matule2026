package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.CartItem
import vrsalex.matule.infrastructure.persisntence.entity.CartItemEntity

fun CartItemEntity.toDomain() = CartItem(
    cartItemId = this.id,
    user = this.user!!.toDomain(),
    product = this.product!!.toDomain(),
    quantity = this.quantity
)

fun CartItem.toEntity() = CartItemEntity(
    id = cartItemId,
    user = user.toEntity(),
    product = product.toEntity(),
    quantity = quantity
)