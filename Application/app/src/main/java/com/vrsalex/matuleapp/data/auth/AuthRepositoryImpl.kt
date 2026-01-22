package com.vrsalex.matuleapp.data.auth

import com.vrsalex.matuleapp.data.local.datastore.DataStoreManager
import com.vrsalex.matuleapp.domain.auth.AuthRepository
import com.vrsalex.matuleapp.domain.auth.AuthResult
import com.vrsalex.matuleapp.domain.auth.AuthResult.*
import com.vrsalex.matuleapp.domain.auth.model.AuthTokens
import com.vrsalex.matuleapp.domain.auth.model.SignUpParams
import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.auth.SignInRequest
import com.vrsalex.network.api.dto.response.AuthResponse
import com.vrsalex.network.api.service.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val dataStoreManager: DataStoreManager
) : AuthRepository {

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult<AuthTokens> {
        return when(val response = authRemoteDataSource.logIn(SignInRequest(email, password))) {
            is NetworkResult.Success<AuthResponse> -> {
                dataStoreManager.saveTokens(response.data.accessToken, response.data.refreshToken)
                Success(response.data.toDomain())
            }
            NetworkResult.Error.NoInternet -> AuthResult.Error.NetworkError
            is NetworkResult.Error.HttpError -> {
                if (response.code == 406) AuthResult.Error.UserNotFound
                else AuthResult.Error.UnknownError
            }
            else -> Error.UnknownError
        }
    }

    override suspend fun signUp(param: SignUpParams): AuthResult<AuthTokens> {
        return when(val response = authRemoteDataSource.signUp(param.toRequest())) {
            is NetworkResult.Success<AuthResponse> -> {
                dataStoreManager.saveTokens(response.data.accessToken, response.data.refreshToken)
                AuthResult.Success(response.data.toDomain())
            }
            NetworkResult.Error.NoInternet -> AuthResult.Error.NetworkError
            else -> AuthResult.Error.UnknownError
        }
    }

    override suspend fun savePinCode(pinCode: String) {
        dataStoreManager.savePinCode(pinCode)
    }

    override fun getPinCode(): Flow<String?> = dataStoreManager.getPinCode()
    override suspend fun matchPinCodes(pinCode: String): Boolean {
        val savedPinCode = dataStoreManager.getPinCode().first()
        return savedPinCode == pinCode
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}