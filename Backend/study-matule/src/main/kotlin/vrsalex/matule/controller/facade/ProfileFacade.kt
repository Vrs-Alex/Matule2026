package vrsalex.matule.controller.facade

import org.springframework.stereotype.Service
import vrsalex.matule.application.command.profile.GetProfileDataCommand
import vrsalex.matule.application.handler.profile.GetProfileDataCommandHandler

@Service
class ProfileFacade(
    private val getProfileDataCommandHandler: GetProfileDataCommandHandler
) {

    fun getProfileData(command: GetProfileDataCommand) = getProfileDataCommandHandler(command)

}