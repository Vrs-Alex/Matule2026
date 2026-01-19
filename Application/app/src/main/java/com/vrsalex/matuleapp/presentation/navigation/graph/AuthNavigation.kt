package com.vrsalex.matuleapp.presentation.navigation.graph

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.vrsalex.matuleapp.presentation.feature.auth.login.LoginScreen
import com.vrsalex.matuleapp.presentation.feature.auth.login.LoginViewModel
import com.vrsalex.matuleapp.presentation.feature.auth.password.CreatePasswordContract
import com.vrsalex.matuleapp.presentation.feature.auth.password.CreatePasswordScreen
import com.vrsalex.matuleapp.presentation.feature.auth.password.CreatePasswordViewModel
import com.vrsalex.matuleapp.presentation.feature.auth.pincode.PinCreateScreen
import com.vrsalex.matuleapp.presentation.feature.auth.profile.CreateProfileScreen
import com.vrsalex.matuleapp.presentation.feature.auth.profile.CreateProfileViewModel
import com.vrsalex.matuleapp.presentation.navigation.AuthGraph
import com.vrsalex.matuleapp.presentation.navigation.CreatePinCodeDestination
import com.vrsalex.matuleapp.presentation.navigation.CreateProfileDestination
import com.vrsalex.matuleapp.presentation.navigation.CreateUserPasswordDestination
import com.vrsalex.matuleapp.presentation.navigation.LogInAndSignUpDestination
import com.vrsalex.matuleapp.presentation.navigation.PinCodeVerifyDestination
import com.vrsalex.matuleapp.presentation.navigation.sharedViewModel

fun NavGraphBuilder.authGraph(navController: NavController){
    navigation<AuthGraph>(
        startDestination = CreatePinCodeDestination
    ){
        composable<LogInAndSignUpDestination> {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(CreateProfileDestination)
                },
                onNavigateToCreatePinCode = {}
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

                }
            )
        }

    }
}