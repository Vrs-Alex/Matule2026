package com.vrsalex.matuleapp.di

import com.vrsalex.matuleapp.data.auth.AuthObserverImpl
import com.vrsalex.matuleapp.data.auth.TokenProviderImpl
import com.vrsalex.network.api.common.AuthObserver
import com.vrsalex.network.api.common.TokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindTokenProvider(impl: TokenProviderImpl): TokenProvider

    @Binds
    @Singleton
    fun bindAuthObserver(impl: AuthObserverImpl): AuthObserver

}