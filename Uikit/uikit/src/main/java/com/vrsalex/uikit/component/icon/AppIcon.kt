package com.vrsalex.uikit.component.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.DefaultIcon

@Composable
fun AppIcon(
    iconId: Int,
    modifier: Modifier = Modifier,
    tint: Color = DefaultIcon,
    onClick: (() -> Unit)? = null
) {

    val clickableModifier = if (onClick != null)
        Modifier.clip(CircleShape).clickable(
            interactionSource = null,
            indication = ripple(),
            onClick = onClick
        ) else Modifier

    Icon(
        painter = painterResource(iconId),
        contentDescription = null,
        tint = tint,
        modifier = modifier.size(20.dp).then(clickableModifier)
    )

}