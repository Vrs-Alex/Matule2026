package com.vrsalex.network.internal.api


import com.vrsalex.network.api.dto.request.auth.RefreshTokenRequest
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.request.auth.SignUpRequest
import com.vrsalex.network.api.dto.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

internal interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: SignInRequest): Response<AuthResponse>

    @POST("auth/signup")
    suspend fun signup(@Body request: SignUpRequest): Response<AuthResponse>

    @GET("auth/logout")
    suspend fun logout(): Response<Unit>

    @POST("auth/update_tokens")
    suspend fun updateTokens(@Body request: RefreshTokenRequest): Response<AuthResponse>

}