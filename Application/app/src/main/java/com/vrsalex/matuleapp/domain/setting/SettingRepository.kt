package com.vrsalex.matuleapp.domain.setting

import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getNotification(): Flow<Boolean>

    suspend fun changeNotification(b: Boolean)


}