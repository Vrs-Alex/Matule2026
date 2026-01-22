package com.vrsalex.matuleapp.presentation.common.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.matuleapp.domain.product.Product
import com.vrsalex.uikit.component.button.AppButton
import com.vrsalex.uikit.component.button.AppButtonState
import com.vrsalex.uikit.component.modal.AppModalBottomSheet
import com.vrsalex.uikit.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductBottomSheet(
    product: Product,
    sheetState: SheetState,
    onButtonClick: (Product) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    AppModalBottomSheet(
        onDismiss = { onDismiss() },
        title = product.title,
        onClose = { onDismiss() },
        sheetState = sheetState,
    ){
        Column(
            modifier.verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp).padding(top = 20.dp)
        ) {
            Text(
                text = "Описание",
                style = AppTheme.type.headlineMedium,
                color = AppTheme.color.caption
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = product.description,
                style = AppTheme.type.textRegular
            )
            Spacer(Modifier.height(49.dp))
            Text(
                text = "Примерный расход:",
                style = AppTheme.type.captionRegular,
                color = AppTheme.color.caption
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = product.weight,
                style = AppTheme.type.headlineMedium
            )
            Spacer(Modifier.height(19.dp))
            AppButton(
                state = AppButtonState.Big,
                onClick = { onButtonClick(product) },
                label = "Добавить за ${product.price}"
            )
            Spacer(Modifier.height(40.dp))
        }
    }

}