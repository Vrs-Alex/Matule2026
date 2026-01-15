package com.vrsalex.uikit.component.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppCardBackground(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {

    val clickableModifier = if (onClick != null)
        Modifier.clickable(
            interactionSource = null,
            indication = ripple(),
            onClick = onClick
        ) else Modifier

    Surface(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(BorderStroke(1.dp, Color(0xFFF4F4F4)), RoundedCornerShape(12.dp))
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x99E4E8F5),
                spotColor = Color(0x99E4E8F5)
            )
            .then(clickableModifier),
        color = Color.Transparent
    ) {
        content()
    }

}