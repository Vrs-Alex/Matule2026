package com.vrsalex.matuleapp.domain.auth

import com.vrsalex.matuleapp.domain.auth.model.AuthTokens
import com.vrsalex.matuleapp.domain.auth.model.SignUpParams
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun signIn(email: String, password: String): AuthResult<AuthTokens>

    suspend fun signUp(param: SignUpParams): AuthResult<AuthTokens>

    suspend fun savePinCode(pinCode: String)

    fun getPinCode(): Flow<String?>

    suspend fun matchPinCodes(pinCode: String): Boolean

    suspend fun logout()

}