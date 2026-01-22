package com.vrsalex.matuleapp.presentation.feature.cart

import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
): BaseViewModel() {

    private val _cart = cartRepository.getCart()

    val state = _cart
        .map {
            CartContract.State(
                cart = it,
                totalPrice = it.totalPrice.toString()
            )
        }.stateInViewModel(CartContract.State())

    private val _chanel = Channel<CartContract.Effect>()
    val effect = _chanel.receiveAsFlow()


    fun onEvent(e: CartContract.Event) {
        when (e) {
            CartContract.Event.OnBack -> {
                viewModelScope.launch { _chanel.send(CartContract.Effect.OnBack) }
            }

            is CartContract.Event.OnIncrementProduct -> {
                viewModelScope.launch {
                    cartRepository.addItem(e.item)
                }
            }

            is CartContract.Event.OnDecrementProduct -> {
                viewModelScope.launch {
                    cartRepository.decrementItem(e.item)
                }
            }

            is CartContract.Event.OnDeleteProduct -> {
                viewModelScope.launch {
                    cartRepository.removeItem(e.item)
                }
            }
            CartContract.Event.OnClearCart -> {
                viewModelScope.launch {
                    cartRepository.clearCart()
                }
            }
        }
    }

}