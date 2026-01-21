package com.vrsalex.matuleapp.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String,
    val email: String
)