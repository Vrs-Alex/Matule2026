package com.vrsalex.matuleapp.presentation.feature.auth.profile

object CreateProfileContract {

    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val patronymic: String = "",
        val birthday: String = "",
        val gender: String? = null,
        val email: String = "",
        val emailError: String? = null
    ){
        val isButtonEnabled = firstName.isNotEmpty() && lastName.isNotEmpty() && patronymic.isNotEmpty()
                && birthday.isNotEmpty() && !gender.isNullOrEmpty() && email.isNotEmpty()
    }

    sealed interface Event {
        data class OnFirstNameChanged(val firstName: String): Event
        data class OnLastNameChanged(val lastName: String): Event
        data class OnPatronymicChanged(val patronymic: String): Event
        data class OnBirthdayChanged(val birthday: String): Event
        data class OnGenderChanged(val gender: String?): Event
        data class OnEmailChanged(val email: String): Event
        data object OnNextClick: Event
    }

    sealed interface Effect {
        data object OnNext: Effect
    }

}