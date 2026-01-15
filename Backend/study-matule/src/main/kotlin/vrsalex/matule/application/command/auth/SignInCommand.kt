package vrsalex.matule.application.command.auth

data class SignInCommand(
    val email: String,
    val password: String
)