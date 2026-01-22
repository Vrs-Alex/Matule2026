package com.vrsalex.matuleapp.presentation.feature.catalog

import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.matuleapp.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val cartRepository: CartRepository
): BaseViewModel() {

    private val _searchedText = MutableStateFlow("")

    private val _selectedCategory = MutableStateFlow<Category?>(null)

    private val _selectedProduct = MutableStateFlow<Product?>(null)

    private val _categories = categoryRepository.getAllCategories()
    private val _products = productRepository.getAllProducts()
    private val _cart = cartRepository.getCart()

    @OptIn(FlowPreview::class)
    private val _filterSearchText =
        _searchedText
            .debounce { 300 }
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



    private val _selectedItems = combine(_selectedCategory, _selectedProduct){
        category, product ->
        category to product
    }

    

    val state: StateFlow<CatalogContract.State> = combine(
        _searchedText,
        _selectedItems,
        _filteredProducts,
        _cart,
        _categories
    ) { text, selectedItems, products, cart, categories ->

        val (category, product) = selectedItems

        CatalogContract.State(
            searchedText = text,
            selectedCategory = category,
            selectedProduct = product,
            products = products,
            categories = categories,
            cartProductIds = cart.productIds,
            cartTotalPrice = cart.totalPrice,
            isLoading = categories.isEmpty()
        )
    }.stateInViewModel(CatalogContract.State(isLoading = true))


    private val _channel = Channel<CatalogContract.Effect>()
    val effect = _channel.receiveAsFlow()




    fun onEvent(e: CatalogContract.Event){
        when(e) {
            is CatalogContract.Event.OnSearch -> _searchedText.update { e.text }
            is CatalogContract.Event.OnSelectCategory -> _selectedCategory.update { e.category }
            is CatalogContract.Event.OnAddProductInCart -> {
                viewModelScope.launch {
                    cartRepository.addItem(CartItem(e.product, 1))
                }
            }
            is CatalogContract.Event.OnRemoveProductInCart -> {
                viewModelScope.launch {
                    cartRepository.removeItem(CartItem(e.product, 1))
                }
            }
            CatalogContract.Event.OnCartClick -> { viewModelScope.launch { _channel.send(CatalogContract.Effect.OnCartClick) } }
            is CatalogContract.Event.OnProductClick -> {
                _selectedProduct.update { e.product }
            }
        }
    }

}