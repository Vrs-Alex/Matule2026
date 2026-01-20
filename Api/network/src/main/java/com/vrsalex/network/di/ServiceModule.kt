package com.vrsalex.network.di

import com.vrsalex.network.api.service.AuthService
import com.vrsalex.network.internal.impl.service.RetrofitAuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceModule {


    @Binds
    @Singleton
    fun bindAuthService(impl: RetrofitAuthService): AuthService


}