package com.vrsalex.network.internal.api

import com.vrsalex.network.api.dto.response.BannerResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.GET

internal interface BannerApi {

    @GET(ServerEndpoints.BANNER_GET_ENDPOINT)
    fun getBanners(): Response<List<BannerResponse>>

}