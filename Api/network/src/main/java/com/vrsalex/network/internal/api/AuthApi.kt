package com.vrsalex.network.internal.api

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.LoginRequest
import com.vrsalex.network.api.dto.request.SignUpRequest
import com.vrsalex.network.api.dto.response.LoginResponse
import com.vrsalex.network.api.dto.response.LogoutResponse
import com.vrsalex.network.api.dto.response.SignUpResponse
import com.vrsalex.network.api.dto.response.UpdateTokensResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

internal interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signup(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET("auth/logout")
    suspend fun logout(): Response<LogoutResponse>

    @POST("auth/update_tokens")
    suspend fun updateTokens(@Header("Authorization") refresh: String): Response<UpdateTokensResponse>

}