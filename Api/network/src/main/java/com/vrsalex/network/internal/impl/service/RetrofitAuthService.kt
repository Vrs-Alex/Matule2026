package com.vrsalex.network.internal.impl.service

import com.vrsalex.network.api.NetworkResult
import com.vrsalex.network.api.dto.request.LoginRequest
import com.vrsalex.network.api.dto.request.SignUpRequest
import com.vrsalex.network.api.dto.response.LoginResponse
import com.vrsalex.network.api.dto.response.LogoutResponse
import com.vrsalex.network.api.dto.response.SignUpResponse
import com.vrsalex.network.api.dto.response.UpdateTokensResponse
import com.vrsalex.network.api.service.AuthService
import com.vrsalex.network.internal.api.AuthApi
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitAuthService @Inject constructor(
    private val api: AuthApi,
    private val json: Json
) : AuthService {

    override suspend fun logIn(request: LoginRequest): NetworkResult<LoginResponse> {
        return safeApiCall { api.login(request) }
    }

    override suspend fun signUp(request: SignUpRequest): NetworkResult<SignUpResponse> {
        return safeApiCall { api.signup(request) }
    }

    override suspend fun logout(): NetworkResult<LogoutResponse> {
        return safeApiCall { api.logout() }
    }

    override suspend fun updateTokens(refresh: String): NetworkResult<UpdateTokensResponse> {
        return safeApiCall { api.updateTokens("Bearer $refresh") }
    }


    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T>{
        return try {
            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error.HttpError(response.code(), "Body is null")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                when (response.code()) {
                    401 -> NetworkResult.Error.Unauthorized
                    else -> NetworkResult.Error.HttpError(response.code(), errorBody)
                }
            }
        } catch (e: HttpException) {
            NetworkResult.Error.HttpError(e.code(), e.message)
        } catch (e: IOException) {
            NetworkResult.Error.NoInternet
        } catch (e: Exception) {
            NetworkResult.Error.Unknown(e)
        }
    }

}