package com.vrsalex.matuleapp.data.banner

import com.vrsalex.matuleapp.data.local.db.dao.BannerDao
import com.vrsalex.matuleapp.domain.banner.Banner
import com.vrsalex.matuleapp.domain.banner.BannerRepository
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.service.BannerRemoteDataSource
import com.vrsalex.network.di.BaseUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepositoryImpl @Inject constructor(
    private val bannerDao: BannerDao,
    private val bannerRemoteDataSource: BannerRemoteDataSource,
    @param:BaseUrl private val baseUrl: String
): BannerRepository {

    override fun getAllBanners(): Flow<List<Banner>> =
        bannerDao.getAllBanners().map { list ->
            list.map { it.toBanner() }
        }

    override suspend fun fetchAndSaveBanners() {
        val response = bannerRemoteDataSource.getBanners()
        if (response is NetworkResult.Success){
            bannerDao.upsertBanners(response.data.map { it.toEntity(baseUrl) })
        }
    }
}