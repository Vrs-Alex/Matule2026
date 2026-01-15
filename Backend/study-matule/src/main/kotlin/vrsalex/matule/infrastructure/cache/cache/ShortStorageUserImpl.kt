package vrsalex.matule.infrastructure.cache.cache

import com.github.benmanes.caffeine.cache.Cache
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import vrsalex.matule.domain.model.User
import vrsalex.matule.domain.model.valueobject.JwtTokens
import vrsalex.matule.domain.port.cache.ShortStorageTokens
import vrsalex.matule.domain.port.cache.ShortStorageUser

@Component
class ShortStorageUserImpl(
    @param:Qualifier("shortStorageUser")
    private val cache: Cache<String, User>
): ShortStorageUser {
    override fun put(phoneNumber: String, user: User) {
        cache.put(phoneNumber, user)
    }

    override fun get(phoneNumber: String): User? {
        return cache.getIfPresent(phoneNumber.lowercase())
    }

    override fun invalidate(phoneNumber: String) {
        cache.invalidate(phoneNumber.lowercase())
    }

}