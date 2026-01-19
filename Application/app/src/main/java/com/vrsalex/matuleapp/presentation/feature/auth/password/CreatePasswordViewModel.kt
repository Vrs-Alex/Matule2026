package com.vrsalex.matuleapp.presentation.feature.auth.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.auth.AuthRegistrationDraft
import com.vrsalex.matuleapp.domain.validation.PasswordValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePasswordViewModel @Inject constructor(
    private val authRegistrationDraft: AuthRegistrationDraft
): ViewModel() {

    private val _state = MutableStateFlow(CreatePasswordContract.State())
    val state = _state.asStateFlow()

    private val _channel = Channel<CreatePasswordContract.Effect>(capacity = Channel.BUFFERED)
    val channel = _channel.receiveAsFlow()

    fun onEvent(e: CreatePasswordContract.Event){
        when(e) {
            is CreatePasswordContract.Event.OnPasswordChanged -> {
                val passwordError = PasswordValidation.validatePassword(e.password)
                _state.update { it.copy(password = e.password, passwordError = passwordError) }
            }
            is CreatePasswordContract.Event.OnConfirmPasswordChanged -> _state.update { it.copy(confirmPassword = e.confirmPassword) }
            CreatePasswordContract.Event.OnNextClick -> {
                if (_state.value.isButtonActive) {
                    viewModelScope.launch {
                        val s = _state.value
                        authRegistrationDraft.update { it.copy(password = s.password) }
                        _channel.send(CreatePasswordContract.Effect.OnNext)
                    }
                }
            }
        }
    }

}