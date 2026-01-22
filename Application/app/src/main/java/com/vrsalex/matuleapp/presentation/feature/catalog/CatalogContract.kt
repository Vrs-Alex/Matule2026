package com.vrsalex.matuleapp.presentation.feature.catalog

import androidx.compose.runtime.Immutable
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.product.Product

object CatalogContract {


    @Immutable
    data class State(
        val searchedText: String = "",
        val selectedCategory: Category? = null,
        val selectedProduct: Product? = null,

        val products: List<Product> = emptyList(),
        val categories: List<Category> = emptyList(),

        val cartProductIds: Set<Long> = emptySet(),
        val cartTotalPrice: Int = 0,

        val isLoading: Boolean = false
    )

    sealed interface Event {
        data class OnSearch(val text: String): Event
        data class OnSelectCategory(val category: Category?): Event

        data class OnAddProductInCart(val product: Product): Event
        data class OnRemoveProductInCart(val product: Product): Event

        data class OnProductClick(val product: Product?): Event
        data object OnCartClick: Event

    }

    sealed interface Effect {
        data object OnCartClick: Effect
    }

}