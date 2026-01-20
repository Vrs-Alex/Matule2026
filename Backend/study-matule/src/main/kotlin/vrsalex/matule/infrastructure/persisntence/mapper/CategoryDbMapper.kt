package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.Category
import vrsalex.matule.infrastructure.persisntence.entity.CategoryEntity

fun CategoryEntity.toDomain() = Category(
    id = this.id,
    name = this.name,
    description = this.description
)

fun Category.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description
)