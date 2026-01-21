package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.network.api.common.TokenProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProviderImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): TokenProvider {

    @Volatile
    private var cachedAccessToken: String? = null

    override suspend fun getRefreshToken(): String? {
        return dataStoreManager.getRefreshToken().first()
    }

    override fun getAccessToken(): String? {
        cachedAccessToken?.let { return it }

        return runBlocking {
            val token = dataStoreManager.getAccessToken().first()
            cachedAccessToken = token
            token
        }
    }

    override suspend fun saveNewTokens(access: String, refresh: String) {
        cachedAccessToken = access
        dataStoreManager.saveAccessToken(access)
        dataStoreManager.saveRefreshToken(refresh)
    }
}