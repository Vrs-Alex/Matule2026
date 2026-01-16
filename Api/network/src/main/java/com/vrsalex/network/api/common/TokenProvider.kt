package com.vrsalex.network.api.common

import kotlinx.coroutines.flow.Flow

interface TokenProvider {

    suspend fun getRefreshToken(): String?

    fun getAccessToken(): String?

    suspend fun saveNewTokens(access: String, refresh: String)

}