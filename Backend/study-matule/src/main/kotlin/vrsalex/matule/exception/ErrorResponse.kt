package vrsalex.matule.exception

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val timestamp: Long = System.currentTimeMillis(),
    val status: Int,
    val message: String
)