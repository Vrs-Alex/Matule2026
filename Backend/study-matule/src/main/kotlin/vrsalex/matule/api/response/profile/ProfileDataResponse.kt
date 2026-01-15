package vrsalex.matule.api.response.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileDataResponse(
    val email: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val birthday: String,
    val gender: String,
    val phoneNum: String
)
