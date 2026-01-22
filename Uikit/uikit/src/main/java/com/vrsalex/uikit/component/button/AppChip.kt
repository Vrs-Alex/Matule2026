package com.vrsalex.uikit.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppChip(
    active: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isFixedWidth: Boolean = true
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (active) AppTheme.color.accent
        else AppTheme.color.inputBg
    )

    val textColor by animateColorAsState(
        targetValue = if (active) AppTheme.color.white
        else AppTheme.color.description
    )

    Surface(
        modifier = modifier.height(48.dp).then(if (isFixedWidth) Modifier.width(129.dp) else Modifier),
        color = backgroundColor,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(
                text = label,
                style = AppTheme.type.textMedium,
                color = textColor
            )
        }
    }

}