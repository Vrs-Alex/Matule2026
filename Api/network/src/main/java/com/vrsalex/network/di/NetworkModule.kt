package com.vrsalex.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vrsalex.network.internal.api.AuthApi
import com.vrsalex.network.internal.impl.AuthAuthenticator
import com.vrsalex.network.internal.impl.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthClient

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val BASE_URL = "http://192.168.0.193/api/"

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .authenticator(authAuthenticator)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    @AuthClient
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofitBuilder(json: Json): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit = retrofitBuilder
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): AuthApi = retrofitBuilder.client(okHttpClient).build().create(AuthApi::class.java)

}