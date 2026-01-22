package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.BannerResponse
import com.vrsalex.network.api.dto.response.UserCartItemResponse

interface CartRemoteDataSource {

    suspend fun getCartItems(): NetworkResult<List<UserCartItemResponse>>

    suspend fun addCartItem(productId: Long): NetworkResult<UserCartItemResponse>

    suspend fun deleteCartItem(cartItemId: Long): NetworkResult<Unit>

    suspend fun clearCart(): NetworkResult<Unit>

}