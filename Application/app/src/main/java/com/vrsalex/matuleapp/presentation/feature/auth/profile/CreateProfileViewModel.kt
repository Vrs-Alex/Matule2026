package com.vrsalex.matuleapp.presentation.feature.auth.profile

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.auth.AuthRegistrationDraft
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val authRegistrationDraft: AuthRegistrationDraft
): ViewModel() {

    private val _state = MutableStateFlow(CreateProfileContract.State())
    val state = _state.asStateFlow()

    private val _channel = Channel<CreateProfileContract.Effect>(capacity = Channel.BUFFERED)
    val channel = _channel.receiveAsFlow()


    fun onEvent(e: CreateProfileContract.Event){
        when(e) {
            is CreateProfileContract.Event.OnFirstNameChanged -> _state.update { it.copy(firstName = e.firstName) }
            is CreateProfileContract.Event.OnLastNameChanged -> _state.update { it.copy(lastName = e.lastName) }
            is CreateProfileContract.Event.OnPatronymicChanged -> _state.update { it.copy(patronymic = e.patronymic) }
            is CreateProfileContract.Event.OnBirthdayChanged -> _state.update { it.copy(birthday = e.birthday) }
            is CreateProfileContract.Event.OnGenderChanged -> _state.update { it.copy(gender = e.gender) }
            is CreateProfileContract.Event.OnEmailChanged -> {
                val emailError = com.vrsalex.matuleapp.domain.validation.EmailValidation.validateEmail(e.email)
                _state.update { it.copy(email = e.email, emailError = emailError) }
            }
            CreateProfileContract.Event.OnNextClick -> {
                if (_state.value.emailError == null) {
                    viewModelScope.launch {
                        val s = _state.value
                        authRegistrationDraft.update {
                            it.copy(
                                firstName = s.firstName,
                                lastName = s.lastName,
                                patronymic = s.patronymic,
                                birthday = s.birthday,
                                gender = s.gender?: "",
                                email = s.email
                            )
                        }
                        _channel.send(CreateProfileContract.Effect.OnNext)
                    }
                }
            }
        }
    }



}