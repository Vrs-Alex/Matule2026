package vrsalex.matule.controller.mapper.auth

import vrsalex.matule.api.request.auth.LogoutRequest
import vrsalex.matule.application.command.auth.LogoutCommand

object LogoutControllerMapper {

    fun toCommand(request: LogoutRequest) = LogoutCommand(request.token)

}