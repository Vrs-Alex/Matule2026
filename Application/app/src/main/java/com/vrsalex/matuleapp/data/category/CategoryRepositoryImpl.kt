package com.vrsalex.matuleapp.data.category

import com.vrsalex.matuleapp.data.local.db.dao.CategoryDao
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.CategoryResponse
import com.vrsalex.network.api.service.CategoryRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories().map { it.map { it.toCategory() } }

    override suspend fun fetchAndSaveCategories() {
        val response = categoryRemoteDataSource.getCategories()
        if (response is NetworkResult.Success<List<CategoryResponse>>) {
            categoryDao.insertCategories(response.data.map { it.toEntity() })
        }
    }
}