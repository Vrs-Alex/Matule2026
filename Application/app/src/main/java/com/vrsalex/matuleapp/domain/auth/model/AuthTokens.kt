package com.vrsalex.matuleapp.domain.auth.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)
