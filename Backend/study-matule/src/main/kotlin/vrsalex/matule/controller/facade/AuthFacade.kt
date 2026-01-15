package vrsalex.matule.controller.facade

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import vrsalex.matule.application.command.auth.LogoutCommand
import vrsalex.matule.application.command.auth.RefreshTokenCommand
import vrsalex.matule.application.command.auth.SignInCommand
import vrsalex.matule.application.command.auth.SignUpCommand
import vrsalex.matule.application.command.auth.VerifySignUpCommand
import vrsalex.matule.application.handler.auth.LogoutCommandHandler
import vrsalex.matule.application.handler.auth.RefreshTokenCommandHandler
import vrsalex.matule.application.handler.auth.SignInCommandHandler
import vrsalex.matule.application.handler.auth.SignUpCommandHandler
import vrsalex.matule.application.handler.auth.VerifySignUpCommandHandler

@Service
class AuthFacade(
    private val signInCommandHandler: SignInCommandHandler,
    private val signUpCommandHandler: SignUpCommandHandler,
    private val verifySignUpCommandHandler: VerifySignUpCommandHandler,
    private val refreshTokenCommandHandler: RefreshTokenCommandHandler,
    private val logoutCommandHandler: LogoutCommandHandler
) {
    @Transactional
    fun signIn(command: SignInCommand) = signInCommandHandler(command)

    @Transactional
    fun signUp(command: SignUpCommand) = signUpCommandHandler(command)

    @Transactional
    fun verifySignUp(command: VerifySignUpCommand) = verifySignUpCommandHandler(command)

    @Transactional
    fun refreshToken(command: RefreshTokenCommand) = refreshTokenCommandHandler(command)

    @Transactional
    fun logout(command: LogoutCommand) = logoutCommandHandler(command)

}