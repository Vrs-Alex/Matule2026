package com.vrsalex.matuleapp.presentation.feature.catalog


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.matuleapp.R
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.matuleapp.presentation.common.product.ProductBottomSheet
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.button.AppCartButton
import com.vrsalex.uikit.component.button.AppChip
import com.vrsalex.uikit.component.button.AppOutlinedButton
import com.vrsalex.uikit.component.card.AppPrimaryCard
import com.vrsalex.uikit.component.search.AppSearch
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    onNavigateToCart: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when(it) {
                CatalogContract.Effect.OnCartClick -> onNavigateToCart()
            }
        }
    }


    CatalogContent(state, viewModel::onEvent)

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    state.selectedProduct?.let {
        ProductBottomSheet(
            product = it,
            sheetState = sheetState,
            onButtonClick = {
                scope.launch {
                    viewModel.onEvent(CatalogContract.Event.OnAddProductInCart(state.selectedProduct!!))
                    sheetState.hide()
                    viewModel.onEvent(CatalogContract.Event.OnProductClick(null))
                }
            },
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                    viewModel.onEvent(CatalogContract.Event.OnProductClick(null))
                }
            }
        )
    }

}



@Composable
private fun CatalogContent(
    state: CatalogContract.State,
    event: (e: CatalogContract.Event) -> Unit
) {
    BaseColumn(withTabBarPadding = true) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn(
                Modifier.clipToBounds(),
                contentPadding = PaddingValues(bottom = 64.dp)
            ) {
                stickyHeader {
                    Box(Modifier.padding(horizontal = 20.dp).padding(top = 24.dp)) {
                        AppSearch(
                            value = state.searchedText,
                            onValueChange = { event(CatalogContract.Event.OnSearch(it)) },
                            placeholder = "Искать  описания",
                            endAction = {
                                Image(
                                    painter = painterResource(R.drawable.user_icon),
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 38.dp).size(32.dp)
                                )
                            }
                        )
                    }
                }

                item(contentType = "categories") {
                    Spacer(Modifier.height(32.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 20.dp)
                    ) {
                        item(
                            contentType = { "category" }
                        ) {
                            AppChip(
                                active = state.selectedCategory == null,
                                label = "Все",
                                onClick = { event(CatalogContract.Event.OnSelectCategory(null)) }
                            )
                        }

                        items(
                            items = state.categories,
                            key = { it.id },
                            contentType = { "category" }
                        ) { category ->
                            AppChip(
                                active = state.selectedCategory?.id == category.id,
                                label = category.name,
                                onClick = { event(CatalogContract.Event.OnSelectCategory(category)) }
                            )
                        }
                    }
                    Spacer(Modifier.height(25.dp))
                }

                items(
                    items = state.products,
                    key = { it.id },
                    contentType = { "product" }
                ) { product ->
                    AppPrimaryCard(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .animateItem()
                            .clickable(
                                interactionSource = null,
                                indication = ripple(),
                                onClick = { event(CatalogContract.Event.OnProductClick(product)) }
                            ),
                        title = product.title,
                        description = product.subtitle,
                        cost = "${product.price} ₽",
                        endContent = {
                            if (state.cartProductIds.contains(product.id)) {
                                AppOutlinedButton(
                                    state = AppButtonState.Small,
                                    onClick = {
                                        event(
                                            CatalogContract.Event.OnRemoveProductInCart(
                                                product
                                            )
                                        )
                                    },
                                    label = "Убрать"
                                )
                            } else {
                                AppButton(
                                    state = AppButtonState.Small,
                                    onClick = {
                                        event(
                                            CatalogContract.Event.OnAddProductInCart(
                                                product
                                            )
                                        )
                                    },
                                    label = "Добавить"
                                )
                            }
                        }
                    )
                    Spacer(Modifier.height(16.dp))
                }
            }
            androidx.compose.animation.AnimatedVisibility (
                visible = state.cartTotalPrice > 0,
                modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 20.dp, vertical = 32.dp),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AppCartButton(
                    price = state.cartTotalPrice,
                    onClick = { event(CatalogContract.Event.OnCartClick) },
                )
            }
        }
    }
}


