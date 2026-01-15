package vrsalex.matule.application.handler.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import vrsalex.matule.application.command.auth.SaveRefreshTokenCommand
import vrsalex.matule.application.command.auth.SignInCommand
import vrsalex.matule.application.exception.exc.InvalidCredentialsException
import vrsalex.matule.application.exception.exc.PhoneVerificationRequiredException
import vrsalex.matule.application.exception.exc.UserNotExistException
import vrsalex.matule.application.result.auth.AuthResult
import vrsalex.matule.domain.port.cache.ShortStorageTokens
import vrsalex.matule.domain.port.repository.UserRepository
import vrsalex.matule.domain.port.security.JwtService

@Component
class SignInCommandHandler(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val saveRefreshTokenCommandHandler: SaveRefreshTokenCommandHandler,
    private val shortStorageTokens: ShortStorageTokens
) {

    operator fun invoke(command: SignInCommand): AuthResult {
        val user = userRepository.findByEmail(command.email)
            ?: throw UserNotExistException("Пользователя с почтой: ${command.email} не существует")

        if (!passwordEncoder.matches(command.password, user.passwordHash))
            throw InvalidCredentialsException("Неверные данные")

        if (!user.verified) {
            // Тут должна быть логика проверки наличия в кэшэ данных о номере (коде), но для учебного проекта это исключаем
            throw PhoneVerificationRequiredException("Нужно подтвердить номер телефона")
        }

        val tokens = jwtService.generateTokens(user.id
            ?: throw UserNotExistException("Пользователя с почтой: ${command.email} не существует"))
        val saveRefreshTokenCommand = SaveRefreshTokenCommand(
            jwtService.getIdFromToken(tokens.refreshToken),
            user.id,
            tokens.refreshToken
        )
        saveRefreshTokenCommandHandler(saveRefreshTokenCommand)

        return tokens
    }


}