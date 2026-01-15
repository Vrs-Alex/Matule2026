package vrsalex.matule.domain.model.valueobject

data class JwtTokens(
    val accessToken: String,
    val refreshToken: String
)
