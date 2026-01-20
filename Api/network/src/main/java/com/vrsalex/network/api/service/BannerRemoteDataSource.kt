package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.BannerResponse

interface BannerRemoteDataSource {

    suspend fun getBanners(): NetworkResult<List<BannerResponse>>

}