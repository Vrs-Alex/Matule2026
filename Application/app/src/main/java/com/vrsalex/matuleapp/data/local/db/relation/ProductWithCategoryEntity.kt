package com.vrsalex.matuleapp.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.vrsalex.matuleapp.data.local.db.entity.CategoryEntity
import com.vrsalex.matuleapp.data.local.db.entity.ProductEntity

data class ProductWithCategoryEntity(
    @Embedded
    val product: ProductEntity,

    @Relation(
        entity = CategoryEntity::class,
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity?
)
