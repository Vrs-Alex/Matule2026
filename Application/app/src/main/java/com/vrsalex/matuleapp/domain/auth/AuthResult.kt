package com.vrsalex.matuleapp.domain.auth

sealed interface AuthResult <out T> {

    data class Success<T>(val data: T) : AuthResult<T>

    sealed interface Error : AuthResult<Nothing> {
        data object NetworkError : Error
        data object WrongPassword : Error
        data object UserNotFound : Error
        data object UnknownError : Error

    }

}