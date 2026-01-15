package com.vrsalex.uikit.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppProjectCard(
    title: String,
    bottomInfo: String,
    bottomContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    AppCardBackground(
        modifier = modifier,
        onClick = null,
    ){
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = title,
                style = AppTheme.type.headlineMedium,
                color = Color.Black
            )
            Spacer(Modifier.height(44.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = bottomInfo,
                    style = AppTheme.type.captionSemibold,
                    color = AppTheme.color.caption
                )
                Spacer(Modifier.weight(1f))
                bottomContent()
            }
        }
    }

}