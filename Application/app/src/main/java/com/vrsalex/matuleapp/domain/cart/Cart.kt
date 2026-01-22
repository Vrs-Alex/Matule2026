package com.vrsalex.matuleapp.domain.cart

data class Cart(
    val items: List<CartItem>
){
    val totalPrice: Int = items.sumOf { it.product.price * it.quantity }

    val productIds = items.map { it.product.id }.toSet()


}
