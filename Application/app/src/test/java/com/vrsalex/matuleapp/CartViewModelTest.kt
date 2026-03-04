package com.vrsalex.matuleapp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.presentation.feature.cart.CartContract
import com.vrsalex.matuleapp.presentation.feature.cart.CartViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private val product = Product(
        id = 1,
        title = "Nike Air Max",
        subtitle = "",
        price = 3000,
        description = "...",
        weight = "200g",
        category = null
    )

    private val cartRepository = mockk<CartRepository>(relaxed = true)
    private lateinit var viewModel: CartViewModel

    private val cartFlow = MutableStateFlow(Cart(items = emptyList()))

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        every { cartRepository.getCart() } returns cartFlow

        viewModel = CartViewModel(cartRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `OnIncrementProduct event should trigger repository addItem`() = runTest {
        // Arrange
        val item = CartItem(product, 1)

        // Act
        viewModel.onEvent(CartContract.Event.OnIncrementProduct(item))

        advanceUntilIdle()

        // Assert: проверяем, что метод был вызван
        coVerify(exactly = 1) { cartRepository.addItem(item) }
    }

    @Test
    fun `OnBack event should emit OnBack effect`() = runTest {
        viewModel.effect.test {
            // Act
            viewModel.onEvent(CartContract.Event.OnBack)

            // Assert
            assertThat(awaitItem()).isEqualTo(CartContract.Effect.OnBack)
        }
    }

    @Test
    fun `OnClearCart should call repository clearCart`() = runTest {
        // Act
        viewModel.onEvent(CartContract.Event.OnClearCart)

        advanceUntilIdle()

        // Assert
        coVerify(exactly = 1) { cartRepository.clearCart() }
    }


    @Test
    fun `OnIncrementProduct event should update state correctly`() = runTest {

        // Имитируем поведение addItem: при вызове меняем значение в нашем потоке
        coEvery { cartRepository.addItem(any()) } answers {
            cartFlow.value = Cart(items = listOf(CartItem(product, 1)))
        }

        viewModel.state.test {
            assertThat(awaitItem().totalPrice).isEmpty()

            // Act
            viewModel.onEvent(CartContract.Event.OnIncrementProduct(CartItem(product, 1)))


            // Assert
            advanceUntilIdle()
            coVerify { cartRepository.addItem(any()) }

            assertThat(awaitItem().cart.totalPrice).isEqualTo(3000)

        }
    }

}