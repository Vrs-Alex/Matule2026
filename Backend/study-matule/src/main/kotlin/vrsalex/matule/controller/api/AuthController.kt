package vrsalex.matule.controller.api


import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.request.auth.LogoutRequest
import vrsalex.matule.api.request.auth.RefreshTokenRequest
import vrsalex.matule.api.request.auth.SignInRequest
import vrsalex.matule.api.request.auth.SignUpRequest
import vrsalex.matule.api.request.auth.VerifySignUpRequest
import vrsalex.matule.api.response.auth.AuthResponse
import vrsalex.matule.application.command.auth.RefreshTokenCommand
import vrsalex.matule.application.command.auth.VerifySignUpCommand
import vrsalex.matule.controller.facade.AuthFacade
import vrsalex.matule.controller.mapper.auth.LogoutControllerMapper
import vrsalex.matule.controller.mapper.auth.RefreshTokenControllerMapper
import vrsalex.matule.controller.mapper.auth.SignInControllerMapper
import vrsalex.matule.controller.mapper.auth.SingUpControllerMapper
import vrsalex.matule.controller.mapper.auth.VerifySignUpControllerMapper

@RestController
class AuthController(
    private val authFacade: AuthFacade
) {


    @PostMapping(ServerEndpoints.API.AUTH_USER_LOGIN_ENDPOINT)
    fun signIn(
        @RequestBody request: SignInRequest
    ): ResponseEntity<AuthResponse> {
        val tokens = authFacade.signIn(SignInControllerMapper.toCommand(request))
        return ResponseEntity.ok(SignInControllerMapper.toResponse(tokens))
    }

    @PostMapping(ServerEndpoints.API.AUTH_USER_REGISTER_ENDPOINT)
    fun signUp(
        @RequestBody request: SignUpRequest
    ): ResponseEntity<Void> {
        authFacade.signUp(SingUpControllerMapper.toCommand(request))
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @PostMapping(ServerEndpoints.API.AUTH_USER_REGISTER_VERIFY_ENDPOINT)
    fun verifySignUp(
        @RequestBody request: VerifySignUpRequest
    ): ResponseEntity<AuthResponse>{
        val tokens = authFacade.verifySignUp(VerifySignUpControllerMapper.toDomain(request))
        return ResponseEntity.ok(VerifySignUpControllerMapper.toResponse(tokens))
    }


    @PostMapping(ServerEndpoints.API.AUTH_REFRESH_TOKEN_ENDPOINT)
    fun refreshToken(
        @RequestBody request: RefreshTokenRequest
    ): ResponseEntity<AuthResponse> {
        val tokens = authFacade.refreshToken(RefreshTokenControllerMapper.toCommand(request))
        return ResponseEntity.ok(RefreshTokenControllerMapper.toResponse(tokens))
    }


    @PostMapping(ServerEndpoints.API.AUTH_USER_LOGOUT_ENDPOINT)
    fun logout(
        @RequestBody request: LogoutRequest
    ): ResponseEntity<Void>{
        authFacade.logout(LogoutControllerMapper.toCommand(request))
        return ResponseEntity.noContent().build()
    }


}