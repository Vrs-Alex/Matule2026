package com.vrsalex.matuleapp.domain.auth

import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.matuleapp.presentation.navigation.AuthGraph
import com.vrsalex.matuleapp.presentation.navigation.CreatePinCodeDestination
import com.vrsalex.matuleapp.presentation.navigation.LogInAndSignUpDestination
import com.vrsalex.matuleapp.presentation.navigation.VerifyPinCodeDestination
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetStartDestinationUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {

    suspend operator fun invoke(): Any {
        val refreshToken = dataStoreManager.getRefreshToken().firstOrNull()
        val pinCode = dataStoreManager.getPinCode().firstOrNull()
        return if (refreshToken == null) LogInAndSignUpDestination
        else if (pinCode == null) CreatePinCodeDestination
        else VerifyPinCodeDestination
    }
}