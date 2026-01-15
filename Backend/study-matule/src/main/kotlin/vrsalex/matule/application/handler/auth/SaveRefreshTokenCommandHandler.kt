package vrsalex.matule.application.handler.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import vrsalex.matule.application.command.auth.SaveRefreshTokenCommand
import vrsalex.matule.domain.model.RefreshToken
import vrsalex.matule.domain.port.repository.RefreshTokenRepository
import vrsalex.matule.domain.port.security.TokenEncoder
import java.time.LocalDateTime
import java.util.UUID

@Component
class SaveRefreshTokenCommandHandler(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenEncoder: TokenEncoder
) {

    operator fun invoke(command: SaveRefreshTokenCommand) {
        val hashedToken = tokenEncoder.encode(command.token)
        val token = RefreshToken(
            id = command.tokenId,
            userId = command.userId,
            tokenHash = hashedToken,
            createdAt = LocalDateTime.now()
        )
        refreshTokenRepository.save(token)
    }

}