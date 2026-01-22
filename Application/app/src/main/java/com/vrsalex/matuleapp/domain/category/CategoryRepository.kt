package com.vrsalex.matuleapp.domain.category

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategories(): Flow<List<Category>>

    suspend fun fetchAndSaveCategories()


}