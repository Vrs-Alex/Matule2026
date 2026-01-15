package vrsalex.matule.application.command.auth

data class VerifySignUpCommand(
    val phoneNumber: String,
    val code: Int
)
