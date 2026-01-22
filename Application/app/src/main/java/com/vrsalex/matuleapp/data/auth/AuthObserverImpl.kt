package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.matuleapp.data.local.db.AppDatabase
import com.vrsalex.network.api.common.AuthObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthObserverImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val appDatabase: AppDatabase
): AuthObserver {

    private val _sharedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    override val logoutEvent: SharedFlow<Unit> = _sharedFlow.asSharedFlow()

    override suspend fun logout() {
        withContext(Dispatchers.IO) {
            dataStoreManager.clearAll()
            appDatabase.clearAllTables()
            _sharedFlow.emit(Unit)
        }
    }

}