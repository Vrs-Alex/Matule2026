package com.vrsalex.matuleapp.domain.project

import android.net.Uri
import java.time.LocalDateTime

data class Project(
    val id: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val type: String,
    val forPerson: String,
    val siteUrl: String,
    val category: String,
    val imageUri: Uri
)
