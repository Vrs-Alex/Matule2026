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
import com.vrsalex.uikit.theme.Black

@Composable
fun AppPrimaryCard(
  title: String,
  description: String,
  cost: String,
  endContent: @Composable () -> Unit,
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
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column() {
                    Text(
                        text = description,
                        style = AppTheme.type.captionSemibold,
                        color = AppTheme.color.caption
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = cost,
                        style = AppTheme.type.title3Semibold,
                        color = Color.Black
                    )
                }
                Spacer(Modifier.weight(1f))
                endContent()
            }
        }
    }


}