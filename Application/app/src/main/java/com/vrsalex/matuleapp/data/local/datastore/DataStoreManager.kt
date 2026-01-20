package com.vrsalex.matuleapp.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "storage")

@Singleton
class DataStoreManager @Inject constructor(
    @param:ApplicationContext private val  context: Context
) {

    suspend fun saveTokens(accessToken: String, refreshToken: String){
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun saveAccessToken(accessToken: String) =
        context.dataStore.edit { it[ACCESS_TOKEN] = accessToken }

    fun getAccessToken() = context.dataStore.data.map { it[ACCESS_TOKEN] }

    suspend fun saveRefreshToken(refreshToken: String) =
        context.dataStore.edit { it[REFRESH_TOKEN] = refreshToken  }

    fun getRefreshToken() = context.dataStore.data.map { it[REFRESH_TOKEN] }

    suspend fun savePinCode(pinCode: String) =
        context.dataStore.edit { it[PIN_CODE] = pinCode }

    fun getPinCode() = context.dataStore.data.map { it[PIN_CODE] }



    companion object {
        val PIN_CODE = stringPreferencesKey("pic_code")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

}