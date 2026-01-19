package com.vrsalex.matuleapp.presentation.common.pincode

object PinCodeContract {

    data class State(
        val pinCode: String = "",
        val codeLength: Int = 4,
        val error: String? = null,
        val title: String = "",
        val subtitle: String = ""
    )

    sealed interface Event {
        data class OnNumberClick(val number: String) : Event
        object OnDeleteClick : Event
    }

    sealed interface Effect {
        object NavigateToMain : Effect
    }

}