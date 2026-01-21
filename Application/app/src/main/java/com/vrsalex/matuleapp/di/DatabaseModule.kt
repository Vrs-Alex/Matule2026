package com.vrsalex.matuleapp.di

import android.content.Context
import androidx.room.Room
import com.vrsalex.matuleapp.data.local.db.AppDatabase
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

}