package com.vrsalex.uikit.component.tabbar

data class AppTabBarItem <T>(
    val payload: T,
    val title: String,
    val iconId: Int
)