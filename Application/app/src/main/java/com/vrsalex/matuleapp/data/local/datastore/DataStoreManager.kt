package com.vrsalex.matuleapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
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


    suspend fun savePinCode(pinCode: String) =
        context.dataStore.edit { it[PIC_CODE] = pinCode }

    fun getPinCode() = context.dataStore.data.map { it[PIC_CODE] }




    companion object {
        val PIC_CODE = stringPreferencesKey("pic_code")
    }

}