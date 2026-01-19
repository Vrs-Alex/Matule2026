package com.vrsalex.matuleapp.presentation.feature.auth.pincode

import androidx.lifecycle.ViewModel
import com.vrsalex.matuleapp.presentation.common.pincode.PinCodeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PinCreateViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(
        PinCodeContract.State(
            title = "Создайте пароль",
            subtitle = "Для защиты ваших персональных данных"
        )
    )
    val state = _state.asStateFlow()

    private val _channel = Channel<PinCodeContract.Effect>()
    val channel = _channel.receiveAsFlow()

    fun onEvent(e: PinCodeContract.Event){
        when(e) {
            is PinCodeContract.Event.OnNumberClick -> {
                _state.update {
                    it.copy(pinCode = it.pinCode + e.number)
                }
            }
            PinCodeContract.Event.OnDeleteClick -> {
                _state.update {
                    it.copy(pinCode = it.pinCode.dropLast(1))
                }
            }
        }
    }

}