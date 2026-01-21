package com.vrsalex.network.api.common

import kotlinx.coroutines.flow.SharedFlow

interface AuthObserver {
    val logoutEvent: SharedFlow<Unit>
    suspend fun logout()
}