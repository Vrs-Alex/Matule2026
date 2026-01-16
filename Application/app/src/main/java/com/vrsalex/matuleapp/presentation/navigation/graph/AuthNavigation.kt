package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vrsalex.matuleapp.presentation.navigation.AuthGraph
import com.vrsalex.matuleapp.presentation.navigation.LogInAndSignUpDestination

fun NavGraphBuilder.authGraph(navController: NavController){
    navigation<AuthGraph>(
        startDestination = LogInAndSignUpDestination
    ){
        composable<LogInAndSignUpDestination> {

        }

    }
}