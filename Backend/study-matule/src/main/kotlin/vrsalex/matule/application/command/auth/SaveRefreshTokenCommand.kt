package vrsalex.matule.application.command.auth

data class SaveRefreshTokenCommand(
    val tokenId: String,
    val userId: Long,
    val token: String
)
