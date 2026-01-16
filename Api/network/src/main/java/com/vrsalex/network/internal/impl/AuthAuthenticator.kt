package com.vrsalex.network.internal.impl

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.common.AuthObserver
import com.vrsalex.network.api.common.TokenProvider
import com.vrsalex.network.api.service.AuthService
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.typeOf

@Singleton
class AuthAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val authObserver: AuthObserver,
    private val authService: AuthService
) : Authenticator {

    private val mutex = Mutex()

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.getTryCount() > 2) return null

        return runBlocking {
            mutex.withLock {
                val currentToken = tokenProvider.getAccessToken()
                val requestToken = response.request.header("Authorization")

                if (currentToken != null && currentToken != requestToken) {
                    return@withLock response.request.newBuilder()
                        .header("Authorization", currentToken)
                        .build()
                }

                val refreshToken = tokenProvider.getRefreshToken()
                if (refreshToken == null) {
                    authObserver.logout()
                    return@withLock null
                }

                when (val result = authService.updateTokens(refreshToken)) {
                    is NetworkResult.Success -> {
                        val newTokens = result.data
                        tokenProvider.saveNewTokens(newTokens.accessToken, newTokens.refreshToken)
                        response.request.newBuilder()
                            .header("Authorization", newTokens.accessToken)
                            .build()
                    }
                    is NetworkResult.Error.Unauthorized -> {
                        authObserver.logout()
                        null
                    }
                    else -> {
                        null
                    }
                }
            }
        }
    }

    private fun Response.getTryCount(): Int {
        var count = 0
        var prior = priorResponse
        while (prior != null){
            count ++
            prior = prior.priorResponse
        }
        return count
    }
}
