package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.response.ProductResponse
import com.vrsalex.network.api.service.ProductRemoteDataSource
import com.vrsalex.network.internal.api.ProductApi
import com.vrsalex.network.internal.common.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitProductDataSource @Inject constructor(
    private val productApi: ProductApi
): ProductRemoteDataSource {
    override suspend fun getProducts(): NetworkResult<List<ProductResponse>> =
        safeApiCall { productApi.getProducts() }
}