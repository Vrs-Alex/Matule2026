package com.vrsalex.uikit.component.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.button.AppBubble
import com.vrsalex.uikit.component.button.AppBubbleSize
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme
import com.vrsalex.uikit.theme.Black

@Composable
fun AppLargeHeader(
    title: String = "",
    onBack: () -> Unit,
    endContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            AppBubble(
                size = AppBubbleSize.Small,
                iconId = R.drawable.icon_chevron_left,
                onClick = { onBack() }
            )
            Spacer(Modifier.weight(1f))
            endContent()
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = title,
            style = AppTheme.type.title1ExtraBold,
            color = Black
        )
    }

}