package com.vrsalex.matuleapp.presentation.feature.project.create

import android.net.Uri

object CreateProjectContract {

    data class State(
        val type: String? = null,
        val name: String = "",
        val startDate: String = "",
        val endDate: String = "",
        val forPerson: String? = null,
        val siteUrl: String = "",
        val category: String? = null,
        val imageUri: Uri? = null,
        val isLoading: Boolean = false,
        val isVisibleBottomSheet: Boolean = false
    )

    sealed interface Event {
        data class OnTypeChange(val type: String?) : Event
        data class OnNameChange(val name: String) : Event
        data class OnStartDateChange(val startDate: String) : Event
        data class OnEndDateChange(val endDate: String) : Event
        data class OnForPersonChange(val person: String?) : Event
        data class OnSiteUrlChange(val siteUrl: String) : Event
        data class OnCategoryChange(val category: String?) : Event
        data class OnUriChange(val uri: Uri?) : Event
        data object OnSelectImage: Event
        data object OnCreate: Event
        data object OnChangeVisibleBottomSheet: Event
    }

    sealed interface Effect {
        data object OnSelectImage: Effect
    }

}