package com.vrsalex.matuleapp.data.cart

import com.vrsalex.matuleapp.data.local.db.dao.CartDao
import com.vrsalex.matuleapp.data.product.toDomain
import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.service.CartRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepositoryImpl @Inject constructor(
    private val cartItemDao: CartDao,
    private val cartRemoteDataSource: CartRemoteDataSource
): CartRepository {
    override fun getCart(): Flow<Cart> =
        cartItemDao.getCartFlow().map { list ->
            val validItems = list.mapNotNull { itemWithProduct ->
                val productEntity = itemWithProduct.product ?: return@mapNotNull null

                CartItem(
                    product = productEntity.toDomain(),
                    quantity = itemWithProduct.cartItem.quantity
                )
            }
            Cart(validItems)
        }

    override suspend fun addItem(item: CartItem) {
        val exists = cartItemDao.getCartItemByProductId(item.product.id) != null
        if (exists) {
            cartItemDao.incrementQuantity(item.product.id)
        } else {
            cartItemDao.insertCartItem(item.toEntity())
        }
        cartRemoteDataSource.addCartItem(item.product.id)
    }

    override suspend fun removeItem(item: CartItem) {
        cartItemDao.deleteById(item.product.id)
        cartRemoteDataSource.deleteCartItem(item.product.id)
    }

    override suspend fun decrementItem(item: CartItem) {
        cartItemDao.decrementQuantity(item.product.id)
        cartRemoteDataSource.deleteCartItem(item.product.id)
    }

    override suspend fun clearCart() {
        cartItemDao.clearCart()
        cartRemoteDataSource.clearCart()
    }

    override suspend fun fetchAndSaveCart() {
        val cart = cartRemoteDataSource.getCartItems()
        if (cart is NetworkResult.Success){
            cartItemDao.insertCartItems(cart.data.map { it.toEntity() })
        }
    }
}