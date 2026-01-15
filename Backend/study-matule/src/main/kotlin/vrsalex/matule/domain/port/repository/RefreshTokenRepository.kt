package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.RefreshToken
import vrsalex.matule.domain.model.User


interface RefreshTokenRepository {
    fun save(refreshToken: RefreshToken): RefreshToken
    fun findById(id: String): RefreshToken?
    fun findByTokenHash(tokenHash: String): RefreshToken?
    fun delete(id: String)
}