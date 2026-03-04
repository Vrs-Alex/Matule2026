package com.vrsalex.matuleapp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.data.cart.CartRepositoryImpl
import com.vrsalex.matuleapp.data.local.db.dao.CartDao
import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.presentation.feature.cart.CartContract
import com.vrsalex.matuleapp.presentation.feature.cart.CartViewModel
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.service.CartRemoteDataSource
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
class CartRepositoryImplTest {

    private val cartItem = CartItem(
        Product(
            id = 101,
            title = "Nike Air Max",
            subtitle = "",
            price = 3000,
            description = "...",
            weight = "200g",
            category = null
        ), 1
    )

    private val cartDao = mockk<CartDao>(relaxed = true)
    private val remoteDataSource = mockk<CartRemoteDataSource>(relaxed = true)
    private lateinit var repository: CartRepositoryImpl

    @BeforeEach
    fun setup() {
        repository = CartRepositoryImpl(cartDao, remoteDataSource)
    }

    @Test
    fun `addItem should increment quantity if item already exists in dao`() = runTest {
        // Arrange
        val productId = 101L

        // Имитируем, что товар уже есть в базе
        coEvery { cartDao.getCartItemByProductId(productId) } returns mockk()

        // Act
        repository.addItem(cartItem)

        // Assert
        advanceUntilIdle()
        coVerify(exactly = 1) { cartDao.incrementQuantity(productId) }
        coVerify(exactly = 0) { cartDao.insertCartItem(any()) } // Вставки быть не должно
        coVerify(exactly = 1) { remoteDataSource.addCartItem(productId) }
    }

    @Test
    fun `addItem should insert new item if it does not exist in dao`() = runTest {
        // Arrange
        val productId = 101L

        // Имитируем, что товара нет
        coEvery { cartDao.getCartItemByProductId(productId) } returns null

        // Act
        repository.addItem(cartItem)

        // Assert
        advanceUntilIdle()
        coVerify(exactly = 1) { cartDao.insertCartItem(any()) }
        coVerify(exactly = 0) { cartDao.incrementQuantity(productId) }
    }


}