package com.vrsalex.matuleapp.presentation.common

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel: ViewModel() {

    fun <T> Flow<T>.stateInViewModel(initValue: T): StateFlow<T> =
        this.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = initValue
        )
}