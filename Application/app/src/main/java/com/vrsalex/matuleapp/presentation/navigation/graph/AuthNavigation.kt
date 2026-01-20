package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vrsalex.matuleapp.presentation.feature.auth.login.LoginScreen
import com.vrsalex.matuleapp.presentation.feature.auth.password.CreatePasswordScreen
import com.vrsalex.matuleapp.presentation.feature.auth.pincode.PinCreateScreen
import com.vrsalex.matuleapp.presentation.feature.auth.pincode.PinVerifyScreen
import com.vrsalex.matuleapp.presentation.feature.auth.profile.CreateProfileScreen
import com.vrsalex.matuleapp.presentation.navigation.AuthGraph
import com.vrsalex.matuleapp.presentation.navigation.BottomTabHomeDestination
import com.vrsalex.matuleapp.presentation.navigation.CreatePinCodeDestination
import com.vrsalex.matuleapp.presentation.navigation.CreateProfileDestination
import com.vrsalex.matuleapp.presentation.navigation.CreateUserPasswordDestination
import com.vrsalex.matuleapp.presentation.navigation.LogInAndSignUpDestination
import com.vrsalex.matuleapp.presentation.navigation.VerifyPinCodeDestination

fun NavGraphBuilder.authGraph(navController: NavController, startedDestination: Any?){
    navigation<AuthGraph>(
        startDestination = startedDestination!!
    ){
        composable<LogInAndSignUpDestination> {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(CreateProfileDestination)
                },
                onNavigateToCreatePinCode = {
                    navController.navigate(CreatePinCodeDestination) {
                        popUpTo(AuthGraph) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<CreateProfileDestination> {
            CreateProfileScreen(
                onNext = {
                    navController.navigate(CreateUserPasswordDestination){
                        popUpTo(CreateProfileDestination)
                    }
                }
            )
        }


        composable<CreateUserPasswordDestination> {
            CreatePasswordScreen(
                onNext = {
                    navController.navigate(CreatePinCodeDestination){
                        popUpTo(CreateUserPasswordDestination){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<CreatePinCodeDestination> {
            PinCreateScreen(
                onNavigateToMain = {
                    navController.navigate(BottomTabHomeDestination){
                        popUpTo(AuthGraph){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<VerifyPinCodeDestination> {
            PinVerifyScreen (
                onNavigateToMain = {
                    navController.navigate(BottomTabHomeDestination){
                        popUpTo(AuthGraph){
                            inclusive = true
                        }
                    }
                }
            )
        }


    }
}