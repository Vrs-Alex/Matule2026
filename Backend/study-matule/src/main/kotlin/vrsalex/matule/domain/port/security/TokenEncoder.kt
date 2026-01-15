package vrsalex.matule.domain.port.security

interface TokenEncoder {
    fun encode(token: String): String
    fun matches(rawToken: String, encodedToken: String): Boolean
}