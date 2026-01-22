package com.vrsalex.matuleapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category")
data class CategoryEntity(
    @PrimaryKey()
    val id: Long,
    val name: String,
    val description: String
)