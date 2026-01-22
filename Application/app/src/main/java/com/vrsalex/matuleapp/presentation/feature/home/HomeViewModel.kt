package com.vrsalex.matuleapp.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.sync.SyncManager
import com.vrsalex.matuleapp.domain.banner.BannerRepository
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.matuleapp.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val bannerRepository: BannerRepository,
    private val cartRepository: CartRepository,
    private val syncManager: SyncManager
): BaseViewModel() {

    private val _searchedText = MutableStateFlow("")

    private val _selectedCategory = MutableStateFlow<Category?>(null)

    private val _banners = bannerRepository.getAllBanners()
    private val _categories = categoryRepository.getAllCategories()
    private val _products = productRepository.getAllProducts()
    private val _cart = cartRepository.getCart()

    @OptIn(FlowPreview::class)
    private val _filterSearchText =
        _searchedText
            .debounce { 400 }
            .distinctUntilChanged()


    private val _filteredProducts = combine(
        _filterSearchText,
        _selectedCategory,
        _products
    ){ searchText, category, products->
        products.filter {
            (searchText.isBlank() || it.title.contains(searchText, ignoreCase = true))
                    && (category == null || it.category?.id == category.id)
        }
    }.flowOn(Dispatchers.IO)


    private val _cartIds = _cart.map { cart ->
        cart.items.map { it.product.id }.toSet()
    }.distinctUntilChanged()

    private val _staticData = combine(_banners, _categories) { banners, categories ->
        banners to categories
    }


    val state: StateFlow<HomeContract.State> = combine(
        _searchedText,
        _selectedCategory,
        _filteredProducts,
        _cartIds,
        _staticData
    ) { text, category, products, cartIds, static ->
        val (banners, categories) = static

        HomeContract.State(
            searchedText = text,
            selectedCategory = category,
            banners = banners,
            products = products,
            categories = categories,
            cartProductIds = cartIds,
            isLoading = banners.isEmpty() && categories.isEmpty()
        )
    }.stateInViewModel(HomeContract.State(isLoading = true))


    fun onEvent(e: HomeContract.Event){
        when(e) {
            is HomeContract.Event.OnSearch -> _searchedText.update { e.text }
            is HomeContract.Event.OnSelectCategory -> _selectedCategory.update { e.category }
            is HomeContract.Event.OnAddProductInCart -> {
                viewModelScope.launch {
                    cartRepository.addItem(CartItem(e.product, 1))
                }
            }
            is HomeContract.Event.OnRemoveProductInCart -> {
                viewModelScope.launch {
                    cartRepository.removeItem(CartItem(e.product, 1))
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            syncManager.sync()
        }
    }


}