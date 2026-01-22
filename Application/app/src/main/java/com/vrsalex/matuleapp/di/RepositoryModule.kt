package com.vrsalex.matuleapp.di

import com.vrsalex.matuleapp.data.auth.AuthRepositoryImpl
import com.vrsalex.matuleapp.data.banner.BannerRepositoryImpl
import com.vrsalex.matuleapp.data.cart.CartRepositoryImpl
import com.vrsalex.matuleapp.data.category.CategoryRepositoryImpl
import com.vrsalex.matuleapp.data.product.ProductRepositoryImpl
import com.vrsalex.matuleapp.data.profile.ProfileRepositoryImpl
import com.vrsalex.matuleapp.data.setting.SettingRepositoryImpl
import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.banner.BannerRepository
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.matuleapp.domain.profile.ProfileRepository
import com.vrsalex.matuleapp.domain.setting.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository


    @Binds
    @Singleton
    fun binsSettingRepository(impl: SettingRepositoryImpl): SettingRepository

    @Binds
    @Singleton
    fun binsProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    fun binsCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindBannerRepository(impl: BannerRepositoryImpl): BannerRepository

    @Binds
    @Singleton
    fun bindCartRepository(impl: CartRepositoryImpl): CartRepository


}