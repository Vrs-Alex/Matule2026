package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.CategoryResponse
import com.vrsalex.network.api.service.CategoryRemoteDataSource
import com.vrsalex.network.internal.api.CategoryApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitCategoryDataSource @Inject constructor(
    private val categoryApi: CategoryApi
): CategoryRemoteDataSource {

    override suspend fun getCategories(): NetworkResult<List<CategoryResponse>> =
        safeApiCall { categoryApi.getCategories() }

}