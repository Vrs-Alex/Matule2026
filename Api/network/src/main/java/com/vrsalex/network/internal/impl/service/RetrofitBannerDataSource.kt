package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.BannerResponse
import com.vrsalex.network.api.service.BannerRemoteDataSource
import com.vrsalex.network.internal.api.BannerApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitBannerDataSource @Inject constructor(
    private val bannerApi: BannerApi
): BannerRemoteDataSource {
    override suspend fun getBanners(): NetworkResult<List<BannerResponse>> =
        safeApiCall { bannerApi.getBanners() }

}