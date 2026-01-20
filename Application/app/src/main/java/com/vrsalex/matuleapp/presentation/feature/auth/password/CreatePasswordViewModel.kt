package com.vrsalex.matuleapp.presentation.feature.auth.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.auth.AuthRegistrationDraft
import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.auth.AuthResult
import com.vrsalex.matuleapp.domain.auth.model.SignUpData
import com.vrsalex.matuleapp.domain.auth.model.SignUpParams
import com.vrsalex.matuleapp.domain.validation.PasswordValidation
import com.vrsalex.matuleapp.presentation.common.snackbar.SnackbarController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePasswordViewModel @Inject constructor(
    private val authRegistrationDraft: AuthRegistrationDraft,
    private val authRepository: AuthRepository,
    private val snackbarController: SnackbarController
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
                        authRegistrationDraft.update { it.copy(password = _state.value.password) }
                        val d = authRegistrationDraft.draft.value

                        val params = SignUpParams(
                            email = d.email,
                            password = d.password,
                            firstName = d.firstName,
                            lastName = d.lastName,
                            patronymic = d.patronymic,
                            birthday = d.birthday,
                            gender = d.gender
                        )

                        val signUpRes = authRepository.signUp(params)

                        if (signUpRes is AuthResult.Success) {
                            _channel.send(CreatePasswordContract.Effect.OnNext)
                        } else {
                            snackbarController.showMessage("Ошибка регистрации")
                        }
                    }
                }
            }
        }
    }

}