package com.vrsalex.uikit.component.controller

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppCounter(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
    minValue: Int = 1
) {
    val minusColor by animateColorAsState(
        targetValue = if (count > minValue) AppTheme.color.caption
        else AppTheme.color.inputIcon
    )

    Row(
        modifier = modifier.height(32.dp).width(64.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(AppTheme.color.inputBg),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = onDecrement
                ),
            contentAlignment = Alignment.Center
        ){
            AppIcon(R.drawable.icon_minus, tint = minusColor)
        }

        Box(Modifier.height(16.dp).width(1.dp).background(AppTheme.color.inputStroke))

        Box(
            Modifier.size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable(
                    interactionSource = null,
                    indication = ripple(),
                    onClick = onIncrement
                ),
            contentAlignment = Alignment.Center
        ){
            AppIcon(R.drawable.icon_plus, tint = AppTheme.color.caption)
        }
    }

}