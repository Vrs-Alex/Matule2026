package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.auth.RefreshTokenRequest
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.request.auth.SignUpRequest
import com.vrsalex.network.api.dto.response.AuthResponse


interface AuthRemoteDataSource {

    suspend fun logIn(request: SignInRequest): NetworkResult<AuthResponse>

    suspend fun signUp(request: SignUpRequest): NetworkResult<AuthResponse>

    suspend fun logout(): NetworkResult<Unit>

    suspend fun updateTokens(request: RefreshTokenRequest): NetworkResult<AuthResponse>

}