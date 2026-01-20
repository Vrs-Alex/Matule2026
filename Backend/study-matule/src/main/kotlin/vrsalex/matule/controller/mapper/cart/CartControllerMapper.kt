package vrsalex.matule.controller.mapper.cart

import vrsalex.matule.api.response.cart.UserCartItemResponse
import vrsalex.matule.controller.mapper.product.toResponse
import vrsalex.matule.domain.model.CartItem

fun CartItem.toResponse() = UserCartItemResponse(
    cartId = this.cartItemId!!,
    product = this.product.toResponse(),
    quantity = this.quantity
)