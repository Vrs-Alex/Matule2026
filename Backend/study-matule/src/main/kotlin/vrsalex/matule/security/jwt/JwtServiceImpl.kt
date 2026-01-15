package vrsalex.matule.security.jwt

import org.springframework.stereotype.Service
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import vrsalex.matule.domain.model.valueobject.JwtTokens
import vrsalex.matule.domain.port.security.JwtService
import vrsalex.matule.security.exception.exc.JwtTokenException
import java.time.Instant
import java.util.Base64
import java.util.Date
import java.util.UUID

@Service
class JwtServiceImpl(
    @param:Value("\${jwt.secret}") private val jwtSecret: String = "",
    @param:Value("\${jwt.token.expiry-refresh-token-ms}") private val expiryRefreshTokenMs: Long,
    @param:Value("\${jwt.token.expiry-access-token-ms}") private val expiryAccessTokenMs: Long,
    @Value("\${jwt.prefix}") private val tokenPrefix: String
): JwtService {

    private val secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))


    override fun generateTokens(userId: Long): JwtTokens {
        return JwtTokens(
            accessToken = generateAccessToken(userId),
            refreshToken = generateRefreshToken(userId)
        )
    }

    fun generateAccessToken(userId: Long, isRememberMe: Boolean? = null): String = generateToken(
        "access",
        userId,
        expiryAccessTokenMs,
        isRememberMe
    )

    fun generateRefreshToken(userId: Long, isRememberMe: Boolean? = null): String = generateToken(
        "refresh",
        userId,
        expiryRefreshTokenMs,
        isRememberMe
    )

    private fun generateToken(
        tokenType: String,
        userId: Long,
        tokenExpirationMs: Long,
        isRememberMe: Boolean? = null
    ): String {
        val now = Instant.now()
        val expiration = now.plusMillis(tokenExpirationMs)

        val jti = UUID.randomUUID().toString()

        return Jwts.builder()
            .id(jti)
            .subject(userId.toString())
            .claim("tokenType", tokenType)
            .claim("isRememberMe", isRememberMe.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(secretKey)
            .compact()
    }


    fun getRememberMeFromToken(token: String): Boolean {
        val claims = parseAllClaims(token)?: throw JwtTokenException("Invalid token")
        val rememberMe = claims["isRememberMe"] as? Boolean ?: throw JwtTokenException("Invalid token")
        return rememberMe
    }

    fun validateAccessToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["tokenType"] as? String ?: return false
        return tokenType == "access"
    }

    override fun validateRefreshToken(token: String): Boolean {
        val claims = parseAllClaims(token) ?: return false
        val tokenType = claims["tokenType"] as? String ?: return false
        return tokenType == "refresh"
    }

    override fun getUserIdFromToken(token: String): Long {
        val claims = parseAllClaims(token)?: throw JwtTokenException("Invalid token")
        val userId = claims.subject.toLongOrNull() ?: throw JwtTokenException("Invalid token payload: User ID is missing or malformed")
        return userId
    }

    override fun getIdFromToken(token: String): String {
        val claims = parseAllClaims(token)?: throw JwtTokenException("Invalid token")
        val jti = claims.id ?: throw JwtTokenException("Invalid token payload: JTI is missing")
        return jti
    }


    private fun parseAllClaims(token: String): Claims? {
        val rawToken = if (token.startsWith(tokenPrefix)) token.substring(tokenPrefix.length)
        else token
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(rawToken)
                .payload
        }
        catch (e: Exception) {
            null
        }
    }


}