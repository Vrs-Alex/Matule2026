package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.auth.AuthResult
import com.vrsalex.matuleapp.domain.auth.SignUpParams
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.LoginRequest
import com.vrsalex.network.api.dto.request.SignUpRequest
import com.vrsalex.network.api.dto.response.LoginResponse
import com.vrsalex.network.api.dto.response.SignUpResponse
import com.vrsalex.network.api.service.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult<Unit> {
        val res = when(val response = authService.logIn(LoginRequest(email, password))) {
            is NetworkResult.Success<LoginResponse> -> AuthResult.Success(Unit)
            NetworkResult.Error.NoInternet -> AuthResult.Error.NetworkError
            else -> AuthResult.Error.UnknownError
        }
        return res
    }

    override suspend fun signUp(param: SignUpParams): AuthResult<Unit> {
        val response = authService.signUp(
            SignUpRequest(
                email = param.email,
                password = param.password,
                firstName = param.firstName,
                lastName = param.lastName,
                patronymic = param.patronymic,
                birthday = param.birthday,
                gender = param.gender
            )
        )
        val res = when(response) {
            is NetworkResult.Success<SignUpResponse> -> AuthResult.Success(Unit)
            NetworkResult.Error.NoInternet -> AuthResult.Error.NetworkError
            else -> AuthResult.Error.UnknownError
        }
        return res
    }
}