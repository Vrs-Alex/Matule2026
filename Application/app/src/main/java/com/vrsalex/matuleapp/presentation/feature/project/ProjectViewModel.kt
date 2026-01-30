package com.vrsalex.matuleapp.presentation.feature.project

import androidx.lifecycle.viewModelScope
import com.vrsalex.matuleapp.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(

): BaseViewModel() {


    private val _state = MutableStateFlow(ProjectContract.State())
    val state = _state.asStateFlow()

    private val _channel = Channel<ProjectContract.Effect>(capacity = Channel.BUFFERED)
    val channel = _channel.receiveAsFlow()

    fun onEvent(e: ProjectContract.Event) {
        when(e) {
            ProjectContract.Event.OnAdd -> {
                viewModelScope.launch {
                    _channel.send(ProjectContract.Effect.OnAdd)
                }
            }
            ProjectContract.Event.OnSync -> {}
        }
    }

}