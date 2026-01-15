package vrsalex.matule.security.jwt

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import vrsalex.matule.domain.port.security.TokenEncoder
import java.security.MessageDigest
import java.util.Base64

@Component
class TokenEncoderImpl : TokenEncoder {
    override fun encode(token: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hashBytes = messageDigest.digest(token.toByteArray())
        return Base64.getEncoder().encodeToString(hashBytes)
    }

    override fun matches(rawToken: String, encodedToken: String): Boolean {
        val encodedRaw = encode(rawToken)
        return encodedRaw == encodedToken
    }
}