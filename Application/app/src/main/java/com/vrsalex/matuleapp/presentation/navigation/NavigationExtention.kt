package com.vrsalex.matuleapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val graphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntity = remember(this) {
        navController.getBackStackEntry(graphRoute)
    }
    return hiltViewModel(parentEntity)
}

