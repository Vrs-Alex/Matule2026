package vrsalex.matule.application.handler.profile

import org.springframework.stereotype.Component
import vrsalex.matule.api.response.profile.ProfileDataResponse
import vrsalex.matule.application.command.profile.GetProfileDataCommand
import vrsalex.matule.application.exception.exc.UserNotExistException
import vrsalex.matule.application.result.profile.ProfileDataResult
import vrsalex.matule.domain.port.repository.UserRepository
import vrsalex.matule.domain.port.security.JwtService

@Component
class GetProfileDataCommandHandler(
    private val userRepository: UserRepository
) {

    operator fun invoke(command: GetProfileDataCommand): ProfileDataResult {
        val user = userRepository.findById(command.userId)
            ?: throw UserNotExistException("Пользователь не найден")

        return ProfileDataResult(
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            patronymic = user.patronymic,
            birthday = user.birthday,
            gender = user.gender,
            phoneNum = user.phoneNum
        )
    }
}