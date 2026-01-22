package com.vrsalex.matuleapp.presentation.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.data.auth.AuthObserverImpl
import com.vrsalex.matuleapp.data.sync.SyncManager
import com.vrsalex.matuleapp.domain.auth.GetStartDestinationUseCase
import com.vrsalex.network.api.common.AuthObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getStartDestinationUseCase: GetStartDestinationUseCase,
    val authObserver: AuthObserver
): ViewModel() {

    private val _startedDestination = MutableStateFlow<Any?>(null)
    val startedDestination = _startedDestination.asStateFlow()


    init {
        viewModelScope.launch {
            supervisorScope() {
                launch {
                    delay(300)
                    _startedDestination.update { getStartDestinationUseCase() }
                }
            }
        }
    }

}