package com.vrsalex.network.internal.api

import com.vrsalex.network.api.dto.response.ProductResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.GET

internal interface ProductApi {

    @GET(ServerEndpoints.PRODUCTS_GET_ENDPOINT)
    fun getProducts(): Response<List<ProductResponse>>


}