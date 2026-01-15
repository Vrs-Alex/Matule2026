package com.vrsalex.uikit.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

enum class AppBubbleSize(val size: Dp){
    Big(48.dp),
    Small(32.dp)
}

@Composable
fun AppBubble(
    size: AppBubbleSize,
    iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.size(size.size)
            .clip(RoundedCornerShape(8.dp))
            .background(AppTheme.color.inputBg)
            .clickable(
                interactionSource = null,
                indication = ripple(),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ){
        AppIcon(iconId, tint = AppTheme.color.description)
    }

}