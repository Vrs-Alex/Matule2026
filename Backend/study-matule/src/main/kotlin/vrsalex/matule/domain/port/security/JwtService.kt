package vrsalex.matule.domain.port.security

import vrsalex.matule.domain.model.valueobject.JwtTokens

interface JwtService {
    fun generateTokens(userId: Long): JwtTokens

    fun validateRefreshToken(token: String): Boolean

    fun getUserIdFromToken(token: String): Long

    fun getIdFromToken(token: String): String
}