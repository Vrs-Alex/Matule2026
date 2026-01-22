package com.vrsalex.matuleapp.presentation.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.vrsalex.matuleapp.presentation.feature.splash.SplashScreen
import com.vrsalex.matuleapp.presentation.navigation.graph.authGraph
import com.vrsalex.matuleapp.presentation.navigation.graph.cartScreen
import com.vrsalex.matuleapp.presentation.navigation.graph.mainGraph

@Composable
fun AppNavHost(
    navHost: NavHostController,
    rootViewModel: RootViewModel = hiltViewModel()
) {
    val startedDestination by rootViewModel.startedDestination.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        rootViewModel.authObserver.logoutEvent.collect {
            navHost.navigate(LogInAndSignUpDestination){
                popUpTo(navHost.graph.id){inclusive = true}
            }
        }
    }

    AnimatedContent(
        startedDestination, transitionSpec = { fadeIn() togetherWith  fadeOut() }
    ) { startDest ->
        if (startDest == null) {
            SplashScreen()
        } else {
            NavHost(
                navController = navHost,
                startDestination = AuthGraph,
                modifier = Modifier.fillMaxSize()
            ) {
                authGraph(navHost, startDest)
                mainGraph(navHost)

                cartScreen(navHost)
            }
        }
    }

}