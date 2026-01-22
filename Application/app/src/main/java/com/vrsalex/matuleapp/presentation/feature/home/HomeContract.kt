package com.vrsalex.matuleapp.presentation.feature.home

import androidx.compose.runtime.Immutable
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.domain.banner.Banner

object HomeContract {

    @Immutable
    data class State(
        val searchedText: String = "",
        val selectedCategory: Category? = null,

        val banners: List<Banner> = emptyList(),

        val products: List<Product> = emptyList(),
        val categories: List<Category> = emptyList(),

        val cartProductIds: Set<Long> = emptySet(),

        val isLoading: Boolean = false
    )


    sealed interface Event {
        data class OnSearch(val text: String): Event
        data class OnSelectCategory(val category: Category?): Event

        data class OnAddProductInCart(val product: Product): Event
        data class OnRemoveProductInCart(val product: Product): Event
    }

}