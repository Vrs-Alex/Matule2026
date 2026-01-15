package com.vrsalex.uikit.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vrsalex.uikit.theme.AppTheme


interface AppButtonState {

    val height: Dp
    val width: Dp?

    @Composable
    fun textStyle(): TextStyle

    object Big: AppButtonState {
        override val height: Dp = 56.dp
        override val width: Dp? = null

        @Composable
        override fun textStyle(): TextStyle = AppTheme.type.title3Semibold

    }

    object Small: AppButtonState {
        override val height: Dp = 40.dp
        override val width: Dp = 96.dp

        @Composable
        override fun textStyle(): TextStyle =  AppTheme.type.captionSemibold

    }

}