package com.vrsalex.matuleapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    indices = [Index(value = ["categoryId"])]
)
data class ProductEntity(
    @PrimaryKey()
    val id: Long,
    val title: String,
    val subtitle: String,
    val price: Int,
    val description: String,
    val weight: String,
    val categoryId: Long
)