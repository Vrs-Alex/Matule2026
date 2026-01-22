package com.vrsalex.matuleapp.domain.product

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>

    suspend fun fetchAndSaveProducts()

}