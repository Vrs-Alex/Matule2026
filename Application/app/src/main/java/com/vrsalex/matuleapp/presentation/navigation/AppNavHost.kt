package com.vrsalex.matuleapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.vrsalex.matuleapp.presentation.feature.splash.SplashScreen
import com.vrsalex.matuleapp.presentation.navigation.graph.authGraph

@Composable
fun AppNavHost(
    navHost: NavHostController,
    innerPadding: PaddingValues,
    rootViewModel: RootViewModel = hiltViewModel()
) {
    val startedDestination by rootViewModel.startedDestination.collectAsStateWithLifecycle()

    if (startedDestination == null) {
        SplashScreen()
    } else {
        NavHost(
            navController = navHost,
            startDestination = startedDestination!!,
            modifier = Modifier.fillMaxSize()
        ) {
            authGraph(navHost)
        }
    }

}