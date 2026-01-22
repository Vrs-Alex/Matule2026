package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vrsalex.matuleapp.presentation.feature.cart.CartScreen
import com.vrsalex.matuleapp.presentation.navigation.CartDestination

fun NavGraphBuilder.cartScreen(navController: NavController){

    composable<CartDestination> {
        CartScreen() {
            navController.popBackStack()
        }
    }

}