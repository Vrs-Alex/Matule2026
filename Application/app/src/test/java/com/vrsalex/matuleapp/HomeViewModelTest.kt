package com.vrsalex.matuleapp

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.vrsalex.matuleapp.data.sync.SyncManager
import com.vrsalex.matuleapp.domain.banner.BannerRepository
import com.vrsalex.matuleapp.domain.cart.Cart
import com.vrsalex.matuleapp.domain.cart.CartItem
import com.vrsalex.matuleapp.domain.cart.CartRepository
import com.vrsalex.matuleapp.domain.category.Category
import com.vrsalex.matuleapp.domain.category.CategoryRepository
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.matuleapp.domain.product.ProductRepository
import com.vrsalex.matuleapp.presentation.feature.home.HomeContract
import com.vrsalex.matuleapp.presentation.feature.home.HomeViewModel
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.Dispatcher
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val productRepository = mockk<ProductRepository>(relaxed = true)
    private val categoryRepository = mockk<CategoryRepository>(relaxed = true)
    private val bannerRepository = mockk<BannerRepository>(relaxed = true)
    private val cartRepository = mockk<CartRepository>(relaxed = true)
    private val syncManager = mockk<SyncManager>(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    // Тестовые данные
    private val testCategories = listOf(Category(id = 1L, name = "Sneakers", description = ""))
    private val testProducts = listOf(
        Product(
            id = 1,
            title = "Nike Air Max",
            subtitle = "",
            price = 3000,
            description = "...",
            weight = "200g",
            category = null
        ),
        Product(
            id = 2,
            title = "Adidas Max",
            subtitle = "",
            price = 4500,
            description = "...",
            weight = "500g",
            category = null
        )
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        every { categoryRepository.getAllCategories() } returns MutableStateFlow(testCategories)
        every { productRepository.getAllProducts() } returns MutableStateFlow(testProducts)
        every { bannerRepository.getAllBanners() } returns MutableStateFlow(emptyList())
        every { cartRepository.getCart() } returns MutableStateFlow(Cart(emptyList()))

        viewModel = HomeViewModel(
            productRepository,
            categoryRepository,
            bannerRepository,
            cartRepository,
            syncManager
        )
    }

    @AfterEach
    fun end(){
        Dispatchers.resetMain()
    }

    @Test
    fun `init should call syncManager sync`() = runTest {
        coVerify(exactly = 1) { syncManager.sync() }
    }


    @Test
    fun `OnSearch event should filter products by text`() = runTest {
        viewModel.state.test {
            awaitItem() // Пропускаем начальный стейт

            // Act
            viewModel.onEvent(HomeContract.Event.OnSearch("Nike"))

            // Продвигаем виртуальное время, чтобы debounce отработал
            advanceTimeBy(401)

            // Assert
            val state = awaitItem()
            assertThat(state.products).hasSize(1)
            assertThat(state.products[0].title).contains("Nike")
        }
    }


    @Test
    fun `OnSelectCategory should filter products by category id`() = runTest {
        viewModel.state.test {
            awaitItem() // Инициализация

            // Act
            viewModel.onEvent(HomeContract.Event.OnSelectCategory(testCategories[0]))

            // Assert
            val state = awaitItem()
            assertThat(state.selectedCategory).isEqualTo(testCategories[0])
            assertThat(state.products.all { it.category?.id == 1L }).isTrue()
        }
    }


    @Test
    fun `state should update cartProductIds when cart changes`() = runTest {
        val cartFlow = MutableStateFlow(Cart(emptyList()))
        every { cartRepository.getCart() } returns cartFlow

        viewModel.state.test {
            assertThat(awaitItem().cartProductIds).isEmpty()
        }
    }

}