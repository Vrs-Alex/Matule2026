package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.CategoryResponse

interface CategoryRemoteDataSource {

    suspend fun getCategories(): NetworkResult<List<CategoryResponse>>

}