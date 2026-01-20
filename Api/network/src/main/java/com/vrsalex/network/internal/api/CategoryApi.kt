package com.vrsalex.network.internal.api

import com.vrsalex.network.api.dto.response.CategoryResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.GET

internal interface CategoryApi {

    @GET(ServerEndpoints.CATEGORY_GET_ENDPOINT)
    fun getCategories(): Response<List<CategoryResponse>>


}