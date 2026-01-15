package vrsalex.matule.application.handler.auth

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import vrsalex.matule.application.command.auth.SaveRefreshTokenCommand
import vrsalex.matule.application.command.auth.SignUpCommand
import vrsalex.matule.application.exception.exc.InvalidCredentialsException
import vrsalex.matule.application.exception.exc.UserExistException
import vrsalex.matule.application.exception.exc.UserNotExistException
import vrsalex.matule.domain.model.User
import vrsalex.matule.domain.port.cache.ShortStorageTokens
import vrsalex.matule.domain.port.cache.ShortStorageUser
import vrsalex.matule.domain.port.repository.UserRepository
import vrsalex.matule.domain.port.security.JwtService

@Component
class SignUpCommandHandler(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val saveRefreshTokenCommandHandler: SaveRefreshTokenCommandHandler,
    private val shortStorageTokens: ShortStorageTokens,
    private val shortStorageUser: ShortStorageUser
) {

    operator fun invoke(command: SignUpCommand) {
        val userExisting = userRepository.findByEmail(command.email)
        if (userExisting != null) throw UserExistException("Пользователь с почтой ${command.email} уже существует")
        if (!validateEmail(command.email)) throw InvalidCredentialsException("Неверная почта")
        if (!validatePhoneNumber(command.phoneNumber)) throw InvalidCredentialsException("Неверный формат номера телефона")
        validatePassword(command.password)
        val passwordHash = passwordEncoder.encode(command.password)
        val user = userRepository.save(
            User(
                id = null,
                email = command.email,
                passwordHash = passwordHash,
                firstName = command.firstName,
                lastName = command.lastName,
                patronymic = command.password,
                birthday = command.birthday,
                gender = command.gender,
                verified = false,
                phoneNum = command.phoneNumber
            )
        )
        val tokens =  jwtService.generateTokens(user.id
            ?: throw UserNotExistException("Пользователя не существует"))

        val saveRefreshTokenCommand = SaveRefreshTokenCommand(
            jwtService.getIdFromToken(tokens.refreshToken),
            user.id,
            tokens.refreshToken
        )
        saveRefreshTokenCommandHandler(saveRefreshTokenCommand)

        // SMS sending needs to be added to production
        shortStorageUser.put(command.phoneNumber, user)
        shortStorageTokens.put(command.phoneNumber,tokens)
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.length == 11
    }

    private fun validatePassword(password: String): Boolean {
        if (password.isNotBlank() && password.length >= 6) return true
        throw InvalidCredentialsException("Минимальная длина пароля 6 символов")
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotBlank() && email.contains("@") && email.length <= 255
    }


}