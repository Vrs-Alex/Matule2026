package com.vrsalex.matuleapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrsalex.matuleapp.data.local.db.dao.BannerDao
import com.vrsalex.matuleapp.data.local.db.dao.CartDao
import com.vrsalex.matuleapp.data.local.db.dao.CategoryDao
import com.vrsalex.matuleapp.data.local.db.dao.ProductDao
import com.vrsalex.matuleapp.data.local.db.dao.UserDao
import com.vrsalex.matuleapp.data.local.db.entity.BannerEntity
import com.vrsalex.matuleapp.data.local.db.entity.CartItemEntity
import com.vrsalex.matuleapp.data.local.db.entity.CategoryEntity
import com.vrsalex.matuleapp.data.local.db.entity.ProductEntity
import com.vrsalex.matuleapp.data.local.db.entity.UserEntity
import com.vrsalex.matuleapp.domain.product.Product

@Database(
    entities = [
        UserEntity::class, BannerEntity::class, CategoryEntity::class,
        ProductEntity::class, CartItemEntity::class
               ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun bannerDao(): BannerDao

    abstract fun categoryDao(): CategoryDao

    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao

    fun clearEverything() {
        this.clearAllTables()
    }

}