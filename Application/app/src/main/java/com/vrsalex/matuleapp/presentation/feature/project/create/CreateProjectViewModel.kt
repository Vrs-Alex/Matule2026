package com.vrsalex.matuleapp.presentation.feature.project.create

import android.content.Context
import androidx.core.content.FileProvider
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.vrsalex.matuleapp.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateProjectViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel() {

    private val _state = MutableStateFlow(CreateProjectContract.State())
    val state = _state.asStateFlow()

    private val _channel = Channel<CreateProjectContract.Effect>()
    val channel = _channel.receiveAsFlow()


    fun onEvent(e: CreateProjectContract.Event) {
        when(e) {
            is CreateProjectContract.Event.OnTypeChange -> _state.update { it.copy(type = e.type) }
            is CreateProjectContract.Event.OnNameChange -> _state.update { it.copy(name = e.name) }
            is CreateProjectContract.Event.OnStartDateChange -> _state.update { it.copy(startDate = e.startDate) }
            is CreateProjectContract.Event.OnEndDateChange -> _state.update { it.copy(endDate = e.endDate) }
            is CreateProjectContract.Event.OnForPersonChange -> _state.update { it.copy(forPerson = e.person) }
            is CreateProjectContract.Event.OnSiteUrlChange -> _state.update { it.copy(siteUrl = e.siteUrl) }
            is CreateProjectContract.Event.OnCategoryChange -> _state.update { it.copy(category = e.category) }
            is CreateProjectContract.Event.OnUriChange -> _state.update { it.copy(imageUri = e.uri) }
            CreateProjectContract.Event.OnCreate -> {

            }
            CreateProjectContract.Event.OnSelectImage -> {
                viewModelScope.launch { _channel.send(CreateProjectContract.Effect.OnSelectImage) }
            }

            CreateProjectContract.Event.OnChangeVisibleBottomSheet -> _state.update { it.copy(isVisibleBottomSheet = !it.isVisibleBottomSheet) }
            CreateProjectContract.Event.OnCreateTempFileForCamera -> {
                val tempFile = File(context.cacheDir, "img_${System.currentTimeMillis()}.jpg")

                val publicUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    tempFile
                )
                _state.update { it.copy(tempPhotoUri = publicUri) }
            }
        }
    }


}