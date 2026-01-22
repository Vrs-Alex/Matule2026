package com.vrsalex.matuleapp.presentation.feature.project

import com.vrsalex.matuleapp.domain.project.Project

object ProjectContract {

    data class State(
        val projects: List<Project> = emptyList(),
        val isLoading: Boolean = false
    )

    sealed interface Event {
        data object OnSync : Event
        data object OnAdd : Event
    }

    sealed interface Effect {
        data object OnAdd : Effect
    }
}