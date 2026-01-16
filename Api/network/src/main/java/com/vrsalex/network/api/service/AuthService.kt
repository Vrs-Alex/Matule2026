package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.LoginRequest
import com.vrsalex.network.api.dto.request.SignUpRequest
import com.vrsalex.network.api.dto.response.LoginResponse
import com.vrsalex.network.api.dto.response.LogoutResponse
import com.vrsalex.network.api.dto.response.SignUpResponse
import com.vrsalex.network.api.dto.response.UpdateTokensResponse

interface AuthService {

    suspend fun logIn(request: LoginRequest): NetworkResult<LoginResponse>

    suspend fun signUp(request: SignUpRequest): NetworkResult<SignUpResponse>

    suspend fun logout(): NetworkResult<LogoutResponse>

    suspend fun updateTokens(refresh: String): NetworkResult<UpdateTokensResponse>

}