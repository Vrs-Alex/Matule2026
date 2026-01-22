package com.vrsalex.matuleapp.presentation.feature.home

import android.R
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.vrsalex.matuleapp.presentation.common.BaseColumn
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.button.AppChip
import com.vrsalex.uikit.component.button.AppOutlinedButton
import com.vrsalex.uikit.component.card.AppPrimaryCard
import com.vrsalex.uikit.component.search.AppSearch
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    HomeContent(state, viewModel::onEvent)
}


@Composable
private fun HomeContent(
    state: HomeContract.State,
    event: (e: HomeContract.Event) -> Unit
) {
    if (state.isLoading){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(color = AppTheme.color.accent)
        }
    } else
    BaseColumn(withTabBarPadding = true) {
        LazyColumn(Modifier.clipToBounds()) {
            stickyHeader {
                Box(Modifier.padding(horizontal = 20.dp).padding(top = 24.dp)) {
                    AppSearch(
                        value = state.searchedText,
                        onValueChange = { event(HomeContract.Event.OnSearch(it)) },
                        placeholder = "Искать  описания"
                    )
                }
            }

            item(contentType = "banners") {
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "Акции и новости",
                    style = AppTheme.type.title3Semibold,
                    color = AppTheme.color.caption,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(
                        items = state.banners,
                        key = { it.id },
                        contentType = { "banner" }
                    ) { banner ->
                        Box(
                            Modifier.height(152.dp)
                                .width(270.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF92E9D4),
                                            Color(0xFF97D9F0)
                                        )
                                    )
                                )
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(
                                    text = banner.title,
                                    style = AppTheme.type.title2ExtraBold,
                                    color = Color.White,
                                    modifier = Modifier.width(150.dp)
                                )
                                Spacer(Modifier.weight(1f))
                                Text(
                                    text = banner.price,
                                    style = AppTheme.type.title2ExtraBold,
                                    color = Color.White
                                )
                            }
                            AsyncImage(
                                model = banner.imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier.fillMaxHeight().align(Alignment.BottomEnd)
                            )
                        }
                    }
                }
            }

            item(contentType = "categories") {
                Spacer(Modifier.height(32.dp))
                Text(
                    text = "Каталог описаний",
                    style = AppTheme.type.title3Semibold,
                    color = AppTheme.color.caption,
                    modifier = Modifier.padding(horizontal = 21.dp)
                )
                Spacer(Modifier.height(15.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    item(
                        contentType = { "category" }
                    ) {
                        AppChip(
                            active = state.selectedCategory == null,
                            label = "Все",
                            onClick = { event(HomeContract.Event.OnSelectCategory(null)) }
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
                            onClick = { event(HomeContract.Event.OnSelectCategory(category)) }
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
                    modifier = Modifier.padding(horizontal = 20.dp).animateItem(),
                    title = product.title,
                    description = product.subtitle,
                    cost = "${product.price} ₽",
                    endContent = {
                        if (state.cartProductIds.contains(product.id)) {
                            AppOutlinedButton(
                                state = AppButtonState.Small,
                                onClick = { event(HomeContract.Event.OnRemoveProductInCart(product)) },
                                label = "Убрать"
                            )
                        } else {
                            AppButton(
                                state = AppButtonState.Small,
                                onClick = { event(HomeContract.Event.OnAddProductInCart(product)) },
                                label = "Добавить"
                            )
                        }
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
