package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.UserCartItemResponse
import com.vrsalex.network.api.service.CartRemoteDataSource
import com.vrsalex.network.internal.api.CartApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitCartDataSource @Inject constructor(
    private val cartApi: CartApi
): CartRemoteDataSource {
    override suspend fun getCartItems(): NetworkResult<List<UserCartItemResponse>> =
        safeApiCall { cartApi.getUserCart() }

    override suspend fun addCartItem(productId: Long): NetworkResult<UserCartItemResponse> =
        safeApiCall { cartApi.addItemInCart(productId) }

    override suspend fun deleteCartItem(cartItemId: Long): NetworkResult<Unit> =
        safeApiCall { cartApi.removeItemFromCart(cartItemId) }

}