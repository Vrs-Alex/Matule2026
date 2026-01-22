package com.vrsalex.matuleapp.domain.cart

import com.vrsalex.matuleapp.domain.product.Product

data class CartItem(
    val product: Product,
    val quantity: Int
)
