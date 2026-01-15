package vrsalex.matule.controller.mapper.auth

import vrsalex.matule.api.request.auth.SignUpRequest
import vrsalex.matule.api.response.auth.AuthResponse
import vrsalex.matule.application.command.auth.SignUpCommand
import vrsalex.matule.application.result.auth.AuthResult

object SingUpControllerMapper {

    fun toCommand(signUpRequest: SignUpRequest): SignUpCommand = SignUpCommand(
        email = signUpRequest.email,
        password = signUpRequest.password,
        firstName = signUpRequest.firstName,
        lastName = signUpRequest.lastName,
        patronymic = signUpRequest.patronymic,
        birthday = signUpRequest.birthday,
        gender = signUpRequest.gender,
        phoneNumber = signUpRequest.phoneNum
    )

    fun toResponse(authResult: AuthResult): AuthResponse = AuthResponse(
        accessToken = authResult.accessToken,
        refreshToken = authResult.refreshToken
    )

}