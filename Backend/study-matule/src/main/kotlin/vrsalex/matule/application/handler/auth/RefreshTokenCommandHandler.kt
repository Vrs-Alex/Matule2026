package vrsalex.matule.application.handler.auth

import org.springframework.stereotype.Component
import vrsalex.matule.application.command.auth.RefreshTokenCommand
import vrsalex.matule.application.command.auth.SaveRefreshTokenCommand
import vrsalex.matule.application.exception.exc.InvalidRefreshTokenException
import vrsalex.matule.application.result.auth.RefreshTokenResult
import vrsalex.matule.domain.model.RefreshToken
import vrsalex.matule.domain.port.repository.RefreshTokenRepository
import vrsalex.matule.domain.port.security.JwtService
import vrsalex.matule.domain.port.security.TokenEncoder

@Component
class RefreshTokenCommandHandler(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenEncoder: TokenEncoder,
    private val jwtService: JwtService,
    private val saveRefreshTokenCommandHandler: SaveRefreshTokenCommandHandler
) {

    operator fun invoke(command: RefreshTokenCommand): RefreshTokenResult{
        if (!jwtService.validateRefreshToken(command.token))
            throw InvalidRefreshTokenException("Неверный токен обновления 1.")
        val tokenId = jwtService.getIdFromToken(command.token)
        val token = refreshTokenRepository.findById(tokenId)
            ?: throw InvalidRefreshTokenException("Неверный токен обновления 2.")

        if (!tokenEncoder.matches(command.token, token.tokenHash))
            throw InvalidRefreshTokenException("Неверный токен обновления 3.")

        val newTokens = jwtService.generateTokens(token.userId)

        refreshTokenRepository.delete(tokenId)

        val saveRefreshTokenCommand = SaveRefreshTokenCommand(
            jwtService.getIdFromToken(newTokens.refreshToken),
            token.userId,
            newTokens.refreshToken
        )
        saveRefreshTokenCommandHandler(saveRefreshTokenCommand)

        return newTokens
    }

}