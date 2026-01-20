package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.Category

interface CategoryRepository {

    fun getAllCategories(): List<Category>

}