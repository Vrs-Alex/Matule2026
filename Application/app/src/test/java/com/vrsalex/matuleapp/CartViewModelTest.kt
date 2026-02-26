package com.vrsalex.matuleapp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.presentation.feature.cart.CartContract
import com.vrsalex.matuleapp.presentation.feature.cart.CartViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    // 1. Мокаем репозиторий
    private val cartRepository = mockk<CartRepository>(relaxed = true)

    // 2. Создаем тестовый диспетчер
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: CartViewModel

    @Before
    fun setup() {
        // Подменяем Dispatchers.Main на тестовый
        Dispatchers.setMain(testDispatcher)

        // Заглушаем дефолтный ответ репозитория (чтобы ViewModel инициализировалась)
        every { cartRepository.getCart() } returns flowOf(Cart(items = emptyList()))

        viewModel = CartViewModel(cartRepository)
    }

    @After
    fun tearDown() {
        // Сбрасываем диспетчер после теста
        Dispatchers.resetMain()
    }


    @Test
    fun `when OnIncrementProduct event, repository addItem should be called`() = runTest {
        val product = Product(
            id = 1, title = "Apple",
            subtitle = "",
            price = 450,
            description = "",
            weight = "",
            category = Category(1, "2", "3"),
        )
        val cartItem = CartItem(product = product, quantity = 1)
        val event = CartContract.Event.OnIncrementProduct(cartItem)

        // Вызываем действие
        viewModel.onEvent(event)

        // Ждем выполнения корутин
        advanceUntilIdle()

        // Проверяем, что метод репозитория БЫЛ вызван с этим объектом
        coVerify(exactly = 1) { cartRepository.addItem(cartItem) }
    }


    @Test
    fun `state should update when repository cart changes`() = runTest {
        // Подменяем данные, которые отдает репозиторий
        val mockCart = Cart(items = listOf())
        val cartFlow = MutableStateFlow(Cart(items = listOf()))
        every { cartRepository.getCart() } returns cartFlow

        // Пересоздаем VM, чтобы она подхватила новый Flow
        viewModel = CartViewModel(cartRepository)

        viewModel.state.test {
            // 1. Сначала прилетает дефолтный стейт
            assertThat(awaitItem().totalPrice).isEqualTo(0)

            // 2. Имитируем изменение в репозитории
            cartFlow.value = mockCart

            // 3. Проверяем, что StateFlow во ViewModel обновил цену
            assertThat(awaitItem().totalPrice).isEqualTo("500")
        }
    }

    @Test
    fun `when OnBack event, OnBack effect should be sent`() = runTest {
        viewModel.effect.test {
            viewModel.onEvent(CartContract.Event.OnBack)

            // Проверяем, что прилетел эффект OnBack
            assertThat(awaitItem()).isEqualTo(CartContract.Effect.OnBack)
        }
    }



    // Твой код
    fun calculateDiscount(price: Double, percent: Int): Double {
        if (percent < 0) return price
        return price * (1 - percent / 100.0)
    }

    // Твой JUnit тест
    @Test
    fun `check discount calculation`() {
        val result = calculateDiscount(100.0, 10)
        // JUnit проверяет: равен ли результат 90.0?
        assertEquals(90.0, result, 0.001)
    }


}