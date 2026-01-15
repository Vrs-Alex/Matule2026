package com.vrsalex.matuleuikit.preview

import androidx.compose.runtime.Composable
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.button.AppBubble
import com.vrsalex.uikit.component.button.AppBubbleSize
import com.vrsalex.uikit.component.header.AppLargeHeader
import com.vrsalex.uikit.component.header.AppSmallHeader
import com.vrsalex.uikit.component.icon.AppIcon
import com.vrsalex.uikit.theme.AppColor
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun ShowHeader() {

    AppSmallHeader(
        title = "Корзина",
        startContent = { AppBubble(AppBubbleSize.Small, R.drawable.icon_chevron_left, {}) },
        endContent = { AppIcon(R.drawable.icon_delete, tint = AppTheme.color.icons) },
    )

    AppLargeHeader(
        title = "Корзина",
        onBack = {},
        endContent = { AppIcon(R.drawable.icon_delete, tint = AppTheme.color.icons) }
    )
    
}