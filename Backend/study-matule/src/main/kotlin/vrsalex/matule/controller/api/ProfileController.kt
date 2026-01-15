package vrsalex.matule.controller.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.profile.ProfileDataResponse
import vrsalex.matule.application.command.profile.GetProfileDataCommand
import vrsalex.matule.controller.facade.ProfileFacade
import vrsalex.matule.domain.model.User

@RestController
class ProfileController(
    private val profileFacade: ProfileFacade
) {

    @GetMapping(ServerEndpoints.API.PROFILE_GET_DATA_ENDPOINT)
    fun getProfileData(
        @AuthenticationPrincipal user: User,
    ): ResponseEntity<ProfileDataResponse> {
        val res = user.id?.let { profileFacade.getProfileData(GetProfileDataCommand(it)) }
        return ResponseEntity.ok(res)
    }

}