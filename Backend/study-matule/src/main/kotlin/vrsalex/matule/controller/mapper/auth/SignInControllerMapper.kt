package vrsalex.matule.controller.mapper.auth

import vrsalex.matule.api.request.auth.SignInRequest
import vrsalex.matule.api.response.auth.AuthResponse
import vrsalex.matule.application.command.auth.SignInCommand
import vrsalex.matule.application.result.auth.AuthResult

object SignInControllerMapper {

    fun toCommand(signInRequest: SignInRequest) = SignInCommand(
        email = signInRequest.email,
        password = signInRequest.password
    )

    fun toResponse(authResult: AuthResult) = AuthResponse(
        accessToken = authResult.accessToken,
        refreshToken = authResult.refreshToken
    )

}