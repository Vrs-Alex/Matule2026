package com.vrsalex.matuleapp.data.category

import com.vrsalex.matuleapp.data.local.db.entity.CategoryEntity
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.network.api.dto.response.CategoryResponse

fun CategoryResponse.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description
)

fun CategoryEntity.toCategory() = Category(
    id = this.id,
    name = this.name,
    description = this.description
)