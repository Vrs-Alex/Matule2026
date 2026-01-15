package vrsalex.matule.domain.port.cache

import vrsalex.matule.domain.model.User
import vrsalex.matule.domain.model.valueobject.JwtTokens

interface ShortStorageUser{

    fun put(phoneNumber: String, user: User)

    fun get(phoneNumber: String): User?

    fun invalidate(phoneNumber: String)
}