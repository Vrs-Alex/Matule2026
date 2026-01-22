package com.vrsalex.matuleapp.domain.profile

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getShortProfileData(): Flow<ShortProfile>

    suspend fun fetchAndSaveProfile()

}