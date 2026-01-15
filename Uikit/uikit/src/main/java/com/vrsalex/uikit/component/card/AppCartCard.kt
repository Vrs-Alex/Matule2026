package com.vrsalex.uikit.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.controller.AppCounter
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppCartCard(
    title: String,
    price: String,
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {

    AppCardBackground(modifier) {
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = AppTheme.type.headlineMedium,
                    color = Color.Black
                )
                AppIcon(R.drawable.icon_close,
                    tint = AppTheme.color.description,
                    onClick = { onCancel() }
                )
            }
            Spacer(Modifier.height(34.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = price,
                    style = AppTheme.type.title3Medium,
                    color = Color.Black
                )
                Spacer(Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$quantity штук",
                        style = AppTheme.type.textRegular,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(42.dp))
                    AppCounter(
                        count = quantity,
                        onIncrement = { onIncrement() },
                        onDecrement = { onDecrement() }
                    )
                }
            }
        }
    }

}