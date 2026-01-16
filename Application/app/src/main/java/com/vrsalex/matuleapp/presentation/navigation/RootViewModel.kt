package com.vrsalex.matuleapp.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(): ViewModel() {

    private val _startedDestination = MutableStateFlow<Any?>(null)
    val startedDestination = _startedDestination.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _startedDestination.update { AuthGraph }
        }
    }

}