package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vrsalex.matuleapp.presentation.navigation.BottomTabCatalogDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabHomeDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProfileDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProjectDestination
import com.vrsalex.matuleapp.presentation.navigation.MainGraph

fun NavGraphBuilder.mainGraph(navController: NavController){

    navigation<MainGraph>(
        startDestination = BottomTabHomeDestination
    ){

        composable<BottomTabHomeDestination> {
            Column(Modifier.fillMaxSize()) { }
        }

        composable<BottomTabCatalogDestination> {  }

        composable<BottomTabProjectDestination> {  }

        composable<BottomTabProfileDestination> {

        }

    }
}