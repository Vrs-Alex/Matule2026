package vrsalex.matule.controller.mapper.product

import vrsalex.matule.api.response.product.ProductResponse
import vrsalex.matule.controller.mapper.category.toResponse
import vrsalex.matule.domain.model.Product

fun Product.toResponse() = ProductResponse(
    id = this.id!!,
    name = this.name,
    subtitle = this.subtitle,
    price = this.price,
    description = this.description,
    category = this.category.toResponse()
)