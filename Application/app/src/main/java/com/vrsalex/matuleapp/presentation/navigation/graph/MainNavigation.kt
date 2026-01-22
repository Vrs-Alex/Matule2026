package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vrsalex.matuleapp.presentation.feature.catalog.CatalogScreen
import com.vrsalex.matuleapp.presentation.feature.home.HomeScreen
import com.vrsalex.matuleapp.presentation.feature.profile.ProfileScreen
import com.vrsalex.matuleapp.presentation.navigation.BottomTabCatalogDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabHomeDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProfileDestination
import com.vrsalex.matuleapp.presentation.navigation.BottomTabProjectDestination
import com.vrsalex.matuleapp.presentation.navigation.CartDestination
import com.vrsalex.matuleapp.presentation.navigation.CreateProjectDestination
import com.vrsalex.matuleapp.presentation.navigation.MainGraph
import com.vrsalex.matuleapp.presentation.feature.project.ProjectScreen
import com.vrsalex.matuleapp.presentation.feature.project.create.CreateProjectScreen

fun NavGraphBuilder.mainGraph(navController: NavController){

    navigation<MainGraph>(
        startDestination = BottomTabHomeDestination
    ){

        composable<BottomTabHomeDestination> {
            HomeScreen()
        }

        composable<BottomTabCatalogDestination> {
            CatalogScreen(){
                navController.navigate(CartDestination)
            }
        }

        composable<BottomTabProjectDestination> {
            ProjectScreen() {
                navController.navigate(CreateProjectDestination)
            }
        }

        composable<CreateProjectDestination> {
            CreateProjectScreen()
        }

        composable<BottomTabProfileDestination> {
            ProfileScreen()
        }

    }
}