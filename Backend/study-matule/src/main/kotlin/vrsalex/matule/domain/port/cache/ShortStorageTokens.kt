package vrsalex.matule.domain.port.cache

import vrsalex.matule.domain.model.valueobject.JwtTokens

interface ShortStorageTokens{

    fun put(phoneNumber: String, tokens: JwtTokens)

    fun get(phoneNumber: String): JwtTokens?

    fun invalidate(phoneNumber: String)
}