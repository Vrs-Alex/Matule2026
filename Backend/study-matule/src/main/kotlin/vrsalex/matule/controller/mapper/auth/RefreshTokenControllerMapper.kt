package vrsalex.matule.controller.mapper.auth

import vrsalex.matule.api.request.auth.RefreshTokenRequest
import vrsalex.matule.api.response.auth.AuthResponse
import vrsalex.matule.application.command.auth.RefreshTokenCommand
import vrsalex.matule.application.result.auth.RefreshTokenResult

object RefreshTokenControllerMapper {

    fun toCommand(request: RefreshTokenRequest) = RefreshTokenCommand(request.token)

    fun toResponse(result: RefreshTokenResult) = AuthResponse(
        accessToken = result.accessToken,
        refreshToken = result.refreshToken
    )

}