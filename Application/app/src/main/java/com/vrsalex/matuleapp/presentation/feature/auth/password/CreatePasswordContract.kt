package com.vrsalex.matuleapp.presentation.feature.auth.password

object CreatePasswordContract {

    data class State(
        val password: String = "",
        val passwordError: String? = null,
        val confirmPassword: String = ""
    ){
        val isButtonActive = password.isNotEmpty() && password == confirmPassword
    }

    sealed interface Event {
        data class OnPasswordChanged(val password: String) : Event
        data class OnConfirmPasswordChanged(val confirmPassword: String) : Event
        data object OnNextClick: Event
    }

    sealed interface Effect {
        data object OnNext: Effect
    }

}