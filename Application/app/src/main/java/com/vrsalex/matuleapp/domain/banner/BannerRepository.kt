package com.vrsalex.matuleapp.domain.banner

import kotlinx.coroutines.flow.Flow

interface BannerRepository {

    fun getAllBanners(): Flow<List<Banner>>

    suspend fun fetchAndSaveBanners()

}