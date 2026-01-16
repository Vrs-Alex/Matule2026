package com.vrsalex.matuleapp.presentation.feature.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.auth.AuthResult
import com.vrsalex.matuleapp.presentation.common.snackbar.SnackbarController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val snackbarController: SnackbarController
): ViewModel() {

    private val _state = MutableStateFlow(LoginContract.State())
    val state = _state.asStateFlow()

    private val _channel = Channel<LoginContract.Effect>(capacity = Channel.BUFFERED)
    val channel = _channel.receiveAsFlow()

    fun onEvent(e: LoginContract.Event){
        when(e) {
            is LoginContract.Event.OnEmailChanged -> _state.update { it.copy(email = e.email) }
            is LoginContract.Event.OnPasswordChanged -> _state.update { it.copy(password = e.password) }
            LoginContract.Event.OnSignInClick -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    val res = authRepository.signIn(_state.value.email, _state.value.password)
                    when(res){
                        is AuthResult.Success<Unit> -> {
                            _state.update { it.copy(isLoading = false) }
                            _channel.send(LoginContract.Effect.SignIn)
                        }
                        AuthResult.Error.NetworkError -> {
                            _state.update { it.copy(isLoading = false) }
                            snackbarController.showMessage("Нет интернета")
                        }
                        else  -> {
                            _state.update { it.copy(isLoading = false) }
                        }
                    }
                }
            }
            LoginContract.Event.SingUpClick -> {
                viewModelScope.launch {
                    _channel.send(LoginContract.Effect.SingUp)
                }
            }

        }
    }

}