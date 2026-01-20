package vrsalex.matule.api.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDataResponse(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String
)
