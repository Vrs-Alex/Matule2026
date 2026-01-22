package com.vrsalex.matuleapp.di

import android.content.Context
import androidx.room.Room
import com.vrsalex.matuleapp.data.local.db.AppDatabase
import com.vrsalex.matuleapp.data.local.db.dao.BannerDao
import com.vrsalex.matuleapp.data.local.db.dao.CartDao
import com.vrsalex.matuleapp.data.local.db.dao.CategoryDao
import com.vrsalex.matuleapp.data.local.db.dao.ProductDao
import com.vrsalex.matuleapp.data.local.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideBanner(db: AppDatabase): BannerDao = db.bannerDao()

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

}