package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.Product
import vrsalex.matule.infrastructure.persisntence.entity.ProductEntity

fun ProductEntity.toDomain() = Product(
    id = this.id,
    name = this.name,
    subtitle = this.subtitle,
    price = this.price,
    description = this.description,
    category = this.category!!.toDomain()
)


fun Product.toEntity() = ProductEntity(
    id = this.id,
    name = this.name,
    subtitle = this.subtitle,
    price = this.price,
    description = this.description,
    category = this.category.toEntity()
)