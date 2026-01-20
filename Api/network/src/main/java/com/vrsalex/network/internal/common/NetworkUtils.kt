package com.vrsalex.network.internal.common

import com.vrsalex.network.api.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

internal suspend inline fun <reified T> safeApiCall(block: suspend () -> Response<T>): NetworkResult<T>{
    try {
        val response = block()
        if (response.isSuccessful) {
            val body = response.body()
            return if (body != null) {
                NetworkResult.Success(body)
            } else {
                val isUnit = Unit is T
                if (isUnit || response.code() == 204) {
                    NetworkResult.Success(Unit as T)
                } else {
                    NetworkResult.Error.HttpError(response.code(), "Required body is missing")
                }
            }
        } else {
            val error = response.errorBody()?.toString()
            return when (response.code()) {
                401 -> NetworkResult.Error.Unauthorized
                else -> NetworkResult.Error.HttpError(response.code(), error)
            }
        }
    } catch (e: HttpException){
        return NetworkResult.Error.HttpError(e.code(), e.message)
    } catch (e: IOException){
        return NetworkResult.Error.NoInternet
    } catch (e: kotlin.Exception){
        return NetworkResult.Error.Unknown(e)
    }

}