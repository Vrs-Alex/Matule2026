package com.vrsalex.matuleapp.domain.profile

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getNotification(): Flow<Boolean>

    fun getProfileData(): Flow<ShortProfile>

    suspend fun changeNotification(b: Boolean)

    suspend fun logout()

    suspend fun fetchAndSaveProfile()

}