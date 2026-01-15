package vrsalex.matule.domain.model

import java.time.LocalDateTime

data class RefreshToken(
    val id: String? = null,
    val userId: Long,
    val tokenHash: String,
    val createdAt: LocalDateTime
)