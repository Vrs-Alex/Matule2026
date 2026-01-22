package com.vrsalex.matuleapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("banner")
data class BannerEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val price: String,
    val imageUrl: String
)
