package com.vrsalex.network.di

import com.vrsalex.network.api.service.AuthRemoteDataSource
import com.vrsalex.network.api.service.BannerRemoteDataSource
import com.vrsalex.network.api.service.CartRemoteDataSource
import com.vrsalex.network.api.service.CategoryRemoteDataSource
import com.vrsalex.network.api.service.ProductRemoteDataSource
import com.vrsalex.network.api.service.ProfileRemoteDataSource
import com.vrsalex.network.internal.impl.service.RetrofitAuthDataSource
import com.vrsalex.network.internal.impl.service.RetrofitBannerDataSource
import com.vrsalex.network.internal.impl.service.RetrofitCartDataSource
import com.vrsalex.network.internal.impl.service.RetrofitCategoryDataSource
import com.vrsalex.network.internal.impl.service.RetrofitProductDataSource
import com.vrsalex.network.internal.impl.service.RetrofitProfileDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun bindAuthDataSource(impl: RetrofitAuthDataSource): AuthRemoteDataSource

    @Binds
    @Singleton
    fun bindBannerDataSource(impl: RetrofitBannerDataSource): BannerRemoteDataSource

    @Binds
    @Singleton
    fun bindCartDataSource(impl: RetrofitCartDataSource): CartRemoteDataSource

    @Binds
    @Singleton
    fun bindCategoryDataSource(impl: RetrofitCategoryDataSource): CategoryRemoteDataSource

    @Binds
    @Singleton
    fun bindProductDataSource(impl: RetrofitProductDataSource): ProductRemoteDataSource

    @Binds
    @Singleton
    fun bindProfileDataSource(impl: RetrofitProfileDataSource): ProfileRemoteDataSource
}