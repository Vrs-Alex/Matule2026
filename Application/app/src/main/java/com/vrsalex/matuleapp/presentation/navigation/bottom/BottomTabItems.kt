package com.vrsalex.matuleapp.presentation.navigation.bottom


import com.vrsalex.matuleapp.presentation.navigation.BottomTabCatalogDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabHomeDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProfileDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProjectDestination
import com.vrsalex.uikit.R
import com.vrsalex.uikit.component.tabbar.AppTabBarItem

val bottomTabItems = listOf<AppTabBarItem<BottomTabDestination>>(
    AppTabBarItem(
        payload = BottomTabHomeDestination,
        title = "Главная",
        iconId = R.drawable.tab_home
    ),
    AppTabBarItem(
        payload = BottomTabCatalogDestination,
        title = "Каталог",
        iconId = R.drawable.tab_catalog
    ),
    AppTabBarItem(
        payload = BottomTabProjectDestination,
        title = "Проекты",
        iconId = R.drawable.tab_project
    ),
    AppTabBarItem(
        payload = BottomTabProfileDestination,
        title = "Профиль",
        iconId = R.drawable.tab_profile
    )
)