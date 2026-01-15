package vrsalex.matule.infrastructure.cache.config

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import vrsalex.matule.domain.model.User
import vrsalex.matule.domain.model.valueobject.JwtTokens
import java.time.Duration

@Configuration
class CaffeineCacheConfig {

    @Bean("shortStorageTokens")
    fun shortStorageTokensCache(): Cache<String, JwtTokens> {
        return Caffeine.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(Duration.ofMinutes(10))
            .build()
    }

    @Bean("shortStorageUser")
    fun shortStorageUserCache(): Cache<String, User> {
        return Caffeine.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(Duration.ofMinutes(10))
            .build()
    }
}