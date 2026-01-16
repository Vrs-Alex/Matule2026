package com.vrsalex.network.api

sealed interface NetworkResult <out T>{

    data class Success<T>(val data: T): NetworkResult<T>

    sealed interface Error : NetworkResult<Nothing> {
        data object NoInternet : Error
        data object Unauthorized : Error
        data class HttpError(val code: Int, val message: String?) : Error
        data class Unknown(val throwable: Throwable) : Error
    }

}