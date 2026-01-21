package com.vrsalex.matuleapp.presentation.feature.profile

object ProfileContract {

    data class State(
        val firstName: String = "",
        val email: String = "",
        val isShowNotification: Boolean = true
    )

    sealed interface Event {
        data class OnChangeNotification(val b: Boolean): Event
        data object OnLogout: Event
        data object OnPrivacyPolicy: Event
        data object OnTermsOfUse: Event
    }

    sealed interface Effect {
        data object OnLogout: Effect
        data class OnPrivacyPolicy(val url: String): Effect
        data class OnTermsOfUse(val url: String): Effect
    }


}