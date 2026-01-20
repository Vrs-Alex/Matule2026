package com.vrsalex.uikit.component.tabbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun <T> AppTabBar(
    items: List<AppTabBarItem<T>>,
    isSelectedItem: (T) -> Boolean,
    onClickItem: (T) -> Unit
) {

    Column(Modifier) {
        HorizontalDivider(thickness = 1.dp, color = Color(0x4DA0A0A0))
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = isSelectedItem(item.payload)
                AppTabBarItem(
                    title = item.title,
                    iconId = item.iconId,
                    isSelected = isSelected
                ) {
                    onClickItem(item.payload)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
    }

}


@Composable
private fun AppTabBarItem(
    title: String,
    iconId: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){

    val elementColor by animateColorAsState(
        if (isSelected) AppTheme.color.accent
        else AppTheme.color.icons
    )

    Column(
        modifier = modifier.height(49.dp)
            .clickable(
                indication = null,
                interactionSource = null,
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.weight(1f),
            painter = painterResource(iconId),
            contentDescription = title,
            tint = elementColor
        )
        Text(
            text = title,
            style = AppTheme.type.caption2Regular,
            color = elementColor
        )
    }
}