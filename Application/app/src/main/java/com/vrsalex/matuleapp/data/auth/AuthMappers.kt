package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.domain.auth.model.AuthTokens
import com.vrsalex.matuleapp.domain.auth.model.SignUpParams
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.request.auth.SignUpRequest
import com.vrsalex.network.api.dto.response.AuthResponse

fun SignUpParams.toRequest() = SignUpRequest(
    email = this.email,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    patronymic = this.password,
    birthday = this.birthday,
    gender = this.gender
)

fun AuthResponse.toDomain() = AuthTokens(
    accessToken = this.accessToken,
    refreshToken = this.refreshToken
)
