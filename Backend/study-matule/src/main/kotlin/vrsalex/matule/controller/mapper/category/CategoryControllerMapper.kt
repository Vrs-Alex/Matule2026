package vrsalex.matule.controller.mapper.category

import vrsalex.matule.api.response.category.CategoryResponse
import vrsalex.matule.domain.model.Category

fun Category.toResponse() = CategoryResponse(
    id = this.id!!,
    name = this.name,
    description = this.description
)