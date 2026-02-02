package com.vrsalex.matuleapp.presentation.feature.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.keepScreenOn
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.card.AppCartCard
import com.vrsalex.uikit.component.header.AppLargeHeader
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when(it) {
                CartContract.Effect.OnBack -> onBack()
            }
        }
    }


    CartContent(state, viewModel::onEvent)
}

@Composable
private fun CartContent(
    state: CartContract.State,
    event: (e: CartContract.Event) -> Unit
) {
    BaseColumn(Modifier.padding(horizontal = 20.dp).padding(top = 16.dp).keepScreenOn()) {
        AppLargeHeader(
            title = "Корзина",
            onBack = { event(CartContract.Event.OnBack) },
            endContent = {
                AppIcon(
                    iconId = R.drawable.icon_delete,
                    tint = AppTheme.color.icons,
                    onClick = { event(CartContract.Event.OnClearCart) }
                )
            }
        )

        LazyColumn(
            Modifier
                .weight(1f)
                .clipToBounds(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(vertical = 32.dp)
        ) {
            items(
                items = state.cart.items,
                key = { it.product.id },
                contentType = { "cart_item" }
            ){ item ->
                AppCartCard(
                    modifier = Modifier.animateItem(),
                    title = item.product.title,
                    price = "${item.product.price} ₽",
                    quantity = item.quantity,
                    onIncrement = {
                        event(CartContract.Event.OnIncrementProduct(item))
                    },
                    onDecrement = {
                        event(CartContract.Event.OnDecrementProduct(item))
                    },
                    onCancel = {
                        event(CartContract.Event.OnDeleteProduct(item))
                    }
                )
            }

            item(contentType = "total_price") {
                Row() {
                    Text(
                        text = "Сумма",
                        style = AppTheme.type.title2Semibold
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "${state.totalPrice} ₽",
                        style = AppTheme.type.title2Semibold
                    )
                }
            }
        }

        AppButton(
            modifier = Modifier.padding(bottom = 32.dp),
            state = AppButtonState.Big,
            onClick = {  },
            label = "Перейти к оформлению заказа"
        )

    }
}