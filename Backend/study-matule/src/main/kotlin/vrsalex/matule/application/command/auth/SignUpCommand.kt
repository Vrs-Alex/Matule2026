package vrsalex.matule.application.command.auth

data class SignUpCommand(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String,
    val phoneNumber: String
)