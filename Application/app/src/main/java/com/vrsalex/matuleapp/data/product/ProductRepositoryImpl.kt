package com.vrsalex.matuleapp.data.product

import android.util.Log
import androidx.room.withTransaction
import com.vrsalex.matuleapp.data.category.toEntity
import com.vrsalex.matuleapp.data.local.db.AppDatabase
import com.vrsalex.matuleapp.data.local.db.dao.CategoryDao
import com.vrsalex.matuleapp.data.local.db.dao.ProductDao
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.service.CategoryRemoteDataSource
import com.vrsalex.network.api.service.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val productRemoteDataSource: ProductRemoteDataSource,
): ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> =
        productDao.getAllProductsWithCategory().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun fetchAndSaveProducts() {
        Log.e("MYAPP", "123".toString())
        val response = productRemoteDataSource.getProducts()
        if (response is NetworkResult.Success){
            val categories = response.data.map { it.category.toEntity() }.distinctBy { it.id }
            val products = response.data.map { it.toEntity() }
            appDatabase.withTransaction {
                categoryDao.insertCategories(categories)
                productDao.insertProducts(products)
            }
        }
    }
}