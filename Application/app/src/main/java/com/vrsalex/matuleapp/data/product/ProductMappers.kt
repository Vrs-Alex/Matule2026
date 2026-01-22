package com.vrsalex.matuleapp.data.product

import com.vrsalex.matuleapp.data.category.toCategory
import com.vrsalex.matuleapp.data.local.db.entity.ProductEntity
import com.vrsalex.matuleapp.data.local.db.relation.ProductWithCategoryEntity
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.network.api.dto.response.ProductResponse
import kotlin.random.Random

fun ProductResponse.toEntity() = ProductEntity(
    id = this.id,
    title = this.name,
    subtitle = this.subtitle,
    price = this.price.toIntOrNull()?: Random.nextInt(100, 4000),
    description = this.description,
    weight = "41 g",
    categoryId = this.category.id
)


fun ProductWithCategoryEntity.toDomain() = Product(
    id = product.id,
    title = product.title,
    subtitle = product.subtitle,
    price = product.price,
    description = product.description,
    weight = product.weight,
    category = category?.toCategory()
)