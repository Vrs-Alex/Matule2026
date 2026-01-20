package com.vrsalex.network.internal.impl.service


import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.auth.RefreshTokenRequest
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.request.auth.SignUpRequest
import com.vrsalex.network.api.dto.response.AuthResponse
import com.vrsalex.network.api.service.AuthRemoteDataSource
import com.vrsalex.network.internal.api.AuthApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class RetrofitAuthDataSource @Inject constructor(
    private val api: AuthApi
) : AuthRemoteDataSource {

    override suspend fun logIn(request: SignInRequest): NetworkResult<AuthResponse> =
        safeApiCall { api.login(request) }


    override suspend fun signUp(request: SignUpRequest): NetworkResult<AuthResponse> =
        safeApiCall { api.signup(request) }


    override suspend fun logout(): NetworkResult<Unit> =
        safeApiCall { api.logout() }


    override suspend fun updateTokens(request: RefreshTokenRequest): NetworkResult<AuthResponse> =
        safeApiCall { api.updateTokens(request) }

}