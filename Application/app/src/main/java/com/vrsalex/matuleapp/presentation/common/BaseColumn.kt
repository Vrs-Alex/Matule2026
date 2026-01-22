package com.vrsalex.matuleapp.presentation.common

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vrsalex.uikit.component.tabbar.appTabBarPadding

@Composable
fun BaseColumn(
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    withTabBarPadding: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .then(
                if (withTabBarPadding) Modifier.appTabBarPadding()
                else Modifier
            )
            .then(
                if (scrollState != null) Modifier.verticalScroll(scrollState)
                else Modifier
            )
    ) {
        content()
    }
}