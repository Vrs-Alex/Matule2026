package com.vrsalex.matuleapp.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrsalex.matuleapp.presentation.common.snackbar.SnackbarController
import com.vrsalex.matuleapp.presentation.common.snackbar.SnackbarViewModel
import com.vrsalex.matuleapp.presentation.navigation.AppNavHost
import com.vrsalex.matuleapp.presentation.navigation.BottomTabDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabHomeDestination
import com.vrsalex.matuleapp.presentation.navigation.MainGraph
import com.vrsalex.matuleapp.presentation.navigation.bottom.bottomTabItems
import com.vrsalex.uikit.component.modal.AppSnackbar
import com.vrsalex.uikit.component.tabbar.AppTabBar
import com.vrsalex.uikit.theme.AppMatuleTheme
import com.vrsalex.uikit.theme.AppTheme
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@Composable
fun MatuleApp() {
    AppMatuleTheme() {
        val navController = rememberNavController()
        val snackBarHostState = remember { SnackbarHostState() }

        val backStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry.value?.destination

        val isShowBottomSheet = remember(currentDestination) {
            currentDestination?.hierarchy?.any { it.hasRoute<MainGraph>() } == true
        }

        val snackbarViewModel = hiltViewModel<SnackbarViewModel>()
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        LaunchedEffect(Unit) {
            snackbarViewModel.snackbarController.messages
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    snackBarHostState.showSnackbar(it)
                }
        }


        Scaffold(
            containerColor = Color.White,
            contentColor = AppTheme.color.black,
            bottomBar = {
                AnimatedVisibility(isShowBottomSheet, enter = slideInVertically { it }, exit = fadeOut()) {
                    AppTabBar<BottomTabDestination>(
                        items = bottomTabItems,
                        isSelectedItem = { payload ->
                            currentDestination?.hierarchy?.any { it.hasRoute(payload::class) } == true
                        },
                        onClickItem = { payload ->
                            navController.navigate(payload){
                                popUpTo(BottomTabHomeDestination){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier.padding(30.dp)
                ){ data ->
                    AppSnackbar(
                        title = data.visuals.message,
                        onClose = { data.dismiss() }
                    )
                }

            }
        ) { innerPadding ->
            AppNavHost(navController, innerPadding)
        }
    }
}