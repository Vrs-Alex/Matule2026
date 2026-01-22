package com.vrsalex.matuleapp.data.setting

import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.matuleapp.domain.setting.SettingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): SettingRepository {

    override fun getNotification(): Flow<Boolean> =
        dataStoreManager.getShowNotification()


    override suspend fun changeNotification(b: Boolean) {
        dataStoreManager.updateShowNotification(b)
    }


}