package com.vrsalex.matuleapp.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.auth.AuthObserverImpl
import com.vrsalex.matuleapp.domain.auth.GetStartDestinationUseCase
import com.vrsalex.network.api.common.AuthObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getStartDestinationUseCase: GetStartDestinationUseCase,
    private val authObserver: AuthObserver
): ViewModel() {

    private val _startedDestination = MutableStateFlow<Any?>(null)
    val startedDestination = _startedDestination.asStateFlow()


    init {
        viewModelScope.launch {
            delay(300)
            _startedDestination.update { getStartDestinationUseCase() }
        }

        viewModelScope.launch {
            authObserver.logoutEvent.collect {
                _startedDestination.update { LogInAndSignUpDestination }
            }
        }
    }

}