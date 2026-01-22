package com.vrsalex.matuleapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.vrsalex.matuleapp.data.local.db.entity.ProductEntity
import com.vrsalex.matuleapp.data.local.db.relation.ProductWithCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    
    @Transaction
    @Query("SELECT * FROM product")
    fun getAllProductsWithCategory(): Flow<List<ProductWithCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("DELETE FROM product")
    suspend fun clearAllBanners()

}