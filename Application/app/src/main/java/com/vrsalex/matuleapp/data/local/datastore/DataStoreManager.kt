package com.vrsalex.matuleapp.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
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

    suspend fun saveRefreshToken(refreshToken: String) =
        context.dataStore.edit { it[REFRESH_TOKEN] = refreshToken  }

    fun getAccessToken() = context.dataStore.data.safeData().map { it[ACCESS_TOKEN] }

    fun getRefreshToken() = context.dataStore.data.safeData().map { it[REFRESH_TOKEN] }



    suspend fun savePinCode(pinCode: String) =
        context.dataStore.edit { it[PIN_CODE] = pinCode }

    fun getPinCode() = context.dataStore.data.safeData().map { it[PIN_CODE] }


    suspend fun updateShowNotification(b: Boolean) =
        context.dataStore.edit { it[SHOW_NOTIFICATION] = b }

    fun getShowNotification() = context.dataStore.data
        .safeData()
        .map { it[SHOW_NOTIFICATION]?: true }



    suspend fun setFirstName(firstName: String) =
        context.dataStore.edit { it[FIRST_NAME] = firstName }

    suspend fun setEmail(email: String) =
        context.dataStore.edit { it[EMAIL] = email }

    fun getFirstName() = context.dataStore.data.safeData().map { it[FIRST_NAME] }

    fun getEmail() = context.dataStore.data.safeData().map { it[EMAIL] }



    suspend fun clearAll(){
        context.dataStore.edit {
            it.clear()
        }
    }

    private fun Flow<Preferences>.safeData(): Flow<Preferences> = this
        .catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }


    companion object {
        val PIN_CODE = stringPreferencesKey("pic_code")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val SHOW_NOTIFICATION = booleanPreferencesKey("show_notification")
        val FIRST_NAME = stringPreferencesKey("first_name")
        val EMAIL = stringPreferencesKey("email")


    }

}