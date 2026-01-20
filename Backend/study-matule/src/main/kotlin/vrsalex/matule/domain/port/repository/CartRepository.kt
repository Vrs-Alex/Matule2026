package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.CartItem
import vrsalex.matule.domain.model.Product

interface CartRepository {
    fun findAllByUserId(userId: Long): List<CartItem>

    fun save(cartItem: CartItem): CartItem

    fun deleteById(cartItemId: Long)

    fun findByUserIdAndProductId(userId: Long, productId: Long): CartItem?

    fun findByCartId(cartId: Long): CartItem?
}