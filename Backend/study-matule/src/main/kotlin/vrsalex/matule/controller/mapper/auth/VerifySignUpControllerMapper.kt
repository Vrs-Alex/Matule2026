package vrsalex.matule.controller.mapper.auth

import vrsalex.matule.api.request.auth.VerifySignUpRequest
import vrsalex.matule.api.response.auth.AuthResponse
import vrsalex.matule.application.command.auth.VerifySignUpCommand
import vrsalex.matule.application.result.auth.AuthResult

object VerifySignUpControllerMapper {

    fun toDomain(request: VerifySignUpRequest) = VerifySignUpCommand(
        phoneNumber = request.phoneNumber,
        code = request.code
    )

    fun toResponse(result: AuthResult) = AuthResponse(
        accessToken = result.accessToken,
        refreshToken = result.refreshToken
    )

}