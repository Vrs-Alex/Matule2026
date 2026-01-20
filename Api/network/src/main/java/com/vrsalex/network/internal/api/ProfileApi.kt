package com.vrsalex.network.internal.api

import com.vrsalex.network.api.dto.response.ProfileDataResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.GET

internal interface ProfileApi {

    @GET(ServerEndpoints.PROFILE_GET_DATA_ENDPOINT)
    fun getProfileData(): Response<ProfileDataResponse>

}