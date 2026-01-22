package com.vrsalex.matuleapp.domain.cart

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<Cart>

    suspend fun addItem(item: CartItem)

    suspend fun removeItem(item: CartItem)

    suspend fun decrementItem(item: CartItem)

    suspend fun clearCart()

    suspend fun fetchAndSaveCart()

}