package com.vrsalex.matuleapp.presentation.feature.cart

import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem

object CartContract {

    data class State(
        val cart: Cart = Cart(emptyList()),
        val totalPrice: String = ""
    )

    sealed interface Event {
        data class OnDeleteProduct(val item: CartItem) : Event
        data class OnIncrementProduct(val item: CartItem) : Event
        data class OnDecrementProduct(val item: CartItem) : Event
        data object OnClearCart : Event
        data object OnBack: Event
    }

    sealed interface Effect {
        data object OnBack: Effect
    }

}