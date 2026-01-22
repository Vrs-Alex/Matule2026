package com.vrsalex.matuleapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cart",
    indices = [Index("productId")]
)
data class CartItemEntity(
    @PrimaryKey
    val productId: Long,
    val quantity: Int
)
