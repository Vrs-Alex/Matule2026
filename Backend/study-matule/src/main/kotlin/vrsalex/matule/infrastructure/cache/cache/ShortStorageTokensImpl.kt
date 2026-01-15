package vrsalex.matule.infrastructure.cache.cache

import com.github.benmanes.caffeine.cache.Cache
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import vrsalex.matule.domain.model.valueobject.JwtTokens
import vrsalex.matule.domain.port.cache.ShortStorageTokens

@Component
class ShortStorageTokensImpl(
    @param:Qualifier("shortStorageTokens")
    private val cache: Cache<String, JwtTokens>
): ShortStorageTokens {
    override fun put(phoneNumber: String, tokens: JwtTokens) {
        cache.put(phoneNumber, tokens)
    }

    override fun get(phoneNumber: String): JwtTokens? {
        return cache.getIfPresent(phoneNumber.lowercase())
    }

    override fun invalidate(phoneNumber: String) {
        cache.invalidate(phoneNumber.lowercase())
    }
}