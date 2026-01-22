package com.vrsalex.network.internal.api

import com.vrsalex.network.api.dto.response.UserCartItemResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CartApi {

    @GET(ServerEndpoints.USER_CART_GET_ENDPOINT)
    suspend fun getUserCart(): Response<List<UserCartItemResponse>>


    @POST(ServerEndpoints.USER_CART_ADD_ENDPOINT)
    suspend fun addItemInCart(
        @Query("product_id") productId: Long
    ): Response<UserCartItemResponse>

    @POST(ServerEndpoints.USER_CART_REMOVE_ALL_ENDPOINT)
    suspend fun clearAllCart(): Response<Unit>

    @DELETE(ServerEndpoints.USER_CART_REMOVE_ENDPOINT)
    suspend fun removeItemFromCart(
        @Path("cart_item_id") cartItemId: Long
    ): Response<Unit>




}