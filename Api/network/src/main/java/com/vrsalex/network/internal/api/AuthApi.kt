package com.vrsalex.network.internal.api


import com.vrsalex.network.api.dto.request.auth.RefreshTokenRequest
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.request.auth.SignUpRequest
import com.vrsalex.network.api.dto.response.AuthResponse
import com.vrsalex.network.internal.common.ServerEndpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

internal interface AuthApi {

    @POST(ServerEndpoints.AUTH_USER_LOGIN_ENDPOINT)
    suspend fun login(@Body request: SignInRequest): Response<AuthResponse>

    @POST(ServerEndpoints.AUTH_USER_REGISTER_ENDPOINT)
    suspend fun signup(@Body request: SignUpRequest): Response<AuthResponse>

    @GET(ServerEndpoints.AUTH_USER_LOGOUT_ENDPOINT)
    suspend fun logout(): Response<Unit>

    @POST(ServerEndpoints.AUTH_REFRESH_TOKEN_ENDPOINT)
    suspend fun updateTokens(@Body request: RefreshTokenRequest): Response<AuthResponse>

}