package com.vrsalex.matuleapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.vrsalex.matuleapp.data.local.db.entity.BannerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {

    @Query("SELECT * FROM banner")
    fun getAllBanners(): Flow<List<BannerEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBanners(banners: List<BannerEntity>): List<Long>

    @Update
    suspend fun updateBanners(banners: List<BannerEntity>)

    @Transaction
    suspend fun upsertBanners(banners: List<BannerEntity>) {
        val insertResults = insertBanners(banners)

        val updateList = banners.filterIndexed { index, _ -> insertResults[index] == -1L }
        if (updateList.isNotEmpty()) {
            updateBanners(updateList)
        }
    }

    @Query("DELETE FROM banner")
    suspend fun clearAllBanners()

}