package vrsalex.matule.api.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(val token: String)
