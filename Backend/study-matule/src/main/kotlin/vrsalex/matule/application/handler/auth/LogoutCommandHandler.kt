package vrsalex.matule.application.handler.auth

import org.springframework.stereotype.Component
import vrsalex.matule.application.command.auth.LogoutCommand
import vrsalex.matule.domain.port.repository.RefreshTokenRepository
import vrsalex.matule.domain.port.security.JwtService

@Component
class LogoutCommandHandler(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JwtService
) {

    operator fun invoke(command: LogoutCommand) {
        val tokenId = jwtService.getIdFromToken(command.token)
       refreshTokenRepository.delete(tokenId)
    }

}