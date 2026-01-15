package vrsalex.matule.application.handler.auth

import org.springframework.stereotype.Component
import vrsalex.matule.api.request.auth.VerifySignUpRequest
import vrsalex.matule.application.command.auth.VerifySignUpCommand
import vrsalex.matule.application.exception.exc.InvalidCredentialsException
import vrsalex.matule.application.exception.exc.TokensExpiredTimeException
import vrsalex.matule.application.result.auth.AuthResult
import vrsalex.matule.domain.port.cache.ShortStorageTokens
import vrsalex.matule.domain.port.cache.ShortStorageUser
import vrsalex.matule.domain.port.repository.UserRepository

@Component
class VerifySignUpCommandHandler(
    private val userRepository: UserRepository,
    private val shortStorageTokens: ShortStorageTokens,
    private val shortStorageUser: ShortStorageUser
) {

    operator fun invoke(command: VerifySignUpCommand): AuthResult {
        // SMS verification needs to be added to production
        if (!validatePhoneNumber(command.phoneNumber))
            throw InvalidCredentialsException("Неверный формат номера телефона")

        val tokens = shortStorageTokens.get(command.phoneNumber)
            ?: throw TokensExpiredTimeException("Время подтверждения номера истекло")

        val user = shortStorageUser.get(command.phoneNumber)
            ?: throw TokensExpiredTimeException("Время подтверждения номера истекло")
        userRepository.save(user.copy(verified = true))

        shortStorageUser.invalidate(command.phoneNumber)
        shortStorageTokens.invalidate(command.phoneNumber)

        return tokens
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.length == 11
    }

}