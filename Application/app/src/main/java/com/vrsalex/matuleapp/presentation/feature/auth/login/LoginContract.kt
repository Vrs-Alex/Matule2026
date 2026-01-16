package com.vrsalex.matuleapp.presentation.feature.auth.login

object LoginContract {

    data class State(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false
    ){
        val isButtonActive = email.isNotEmpty() && password.isNotEmpty() && !isLoading
    }

    sealed interface Event {
        data class OnEmailChanged(val email: String) : Event
        data class OnPasswordChanged(val password: String) : Event
        data object SingUpClick: Event
        data object OnSignInClick: Event
    }

    sealed interface Effect {
        data object SingUp: Effect
        data object SignIn: Effect
    }
}
