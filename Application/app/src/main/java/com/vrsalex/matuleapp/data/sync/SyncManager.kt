package com.vrsalex.matuleapp.data.sync

import android.util.Log
import com.vrsalex.matuleapp.data.cart.CartRepositoryImpl
import com.vrsalex.matuleapp.domain.banner.BannerRepository
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.matuleapp.domain.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncManager @Inject constructor(
    private val bannerRepository: BannerRepository,
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
    private val cartRepositoryImpl: CartRepositoryImpl,
    private val profileRepository: ProfileRepository
) {

    suspend fun sync() = withContext(Dispatchers.IO){
        supervisorScope {
            launch {
                runCatching { bannerRepository.fetchAndSaveBanners() }
                    .onFailure { Log.e("Sync", "Banners failed", it) }
            }

            launch {
                runCatching {
                    categoryRepository.fetchAndSaveCategories()
                    productRepository.fetchAndSaveProducts()
                    cartRepositoryImpl.fetchAndSaveCart()
                }.onFailure { Log.e("Sync", "Products/Cart chain failed", it) }
            }

            launch {
                runCatching { profileRepository.fetchAndSaveProfile() }
                    .onFailure { Log.e("Sync", "Profile failed", it) }
            }
        }
    }
}