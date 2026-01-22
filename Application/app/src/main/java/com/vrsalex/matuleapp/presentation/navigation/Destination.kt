package com.vrsalex.matuleapp.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
data object AuthGraph

@Serializable
data object VerifyPinCodeDestination

@Serializable
data object LogInAndSignUpDestination

@Serializable
data object CreateProfileDestination

@Serializable
data object CreateUserPasswordDestination

@Serializable
data object CreatePinCodeDestination





@Serializable
data object MainGraph


sealed interface BottomTabDestination

@Serializable
data object BottomTabHomeDestination: BottomTabDestination

@Serializable
data object BottomTabCatalogDestination: BottomTabDestination

@Serializable
data object BottomTabProjectDestination: BottomTabDestination

@Serializable
data object BottomTabProfileDestination: BottomTabDestination




@Serializable
data object CartDestination


@Serializable
data object CreateProjectDestination


