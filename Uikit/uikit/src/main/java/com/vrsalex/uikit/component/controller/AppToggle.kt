package com.vrsalex.uikit.component.controller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme

@Composable
fun AppToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Switch(
        checked = checked,
        onCheckedChange = { onCheckedChange(it) },
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = AppTheme.color.white,
            uncheckedThumbColor = AppTheme.color.white,
            checkedTrackColor = AppTheme.color.accent,
            uncheckedTrackColor = AppTheme.color.inputStroke,
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = Color.Transparent
        ),
        thumbContent = {
            Box(Modifier.size(24.dp).clip(CircleShape).background(AppTheme.color.white))
        }
    )

}