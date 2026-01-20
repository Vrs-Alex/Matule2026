package com.vrsalex.network.api.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.ProductResponse

interface ProductRemoteDataSource {

    suspend fun getProducts(): NetworkResult<List<ProductResponse>>
}