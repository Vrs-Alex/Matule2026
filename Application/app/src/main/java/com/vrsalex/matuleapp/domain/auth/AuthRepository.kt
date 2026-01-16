package com.vrsalex.matuleapp.domain.auth

interface AuthRepository {

    suspend fun signIn(email: String, password: String): AuthResult<Unit>

    suspend fun signUp(param: SignUpParams): AuthResult<Unit>

}