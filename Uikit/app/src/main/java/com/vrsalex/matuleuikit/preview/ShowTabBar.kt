package com.vrsalex.matuleuikit.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.tabbar.AppTabBar
import com.vrsalex.uikit.component.tabbar.AppTabBarItem


private val previewData = listOf(
    AppTabBarItem(
        payload = "1",
        title = "Главная",
        iconId = R.drawable.tab_home
    ),
    AppTabBarItem(
        payload = "2",
        title = "Каталог",
        iconId = R.drawable.tab_catalog
    ),
    AppTabBarItem(
        payload = "3",
        title = "Проект",
        iconId = R.drawable.tab_project
    ),
    AppTabBarItem(
        payload = "4",
        title = "Профиль",
        iconId = R.drawable.tab_profile
    )
)


@Composable
fun ShowTabBar() {

    var selectedItem by remember { mutableStateOf(previewData[0].payload) }

    AppTabBar(
        items = previewData,
        isSelectedItem = { selectedItem == it },
        onClickItem = { p -> selectedItem = p }
    )

}