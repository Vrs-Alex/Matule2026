package vrsalex.matule.api.request.project

import kotlinx.serialization.Serializable

@Serializable
data class AddProjectRequest(
    val id: Long?,
    val name: String,
    val startDate: String,
    val endDate: String,
    val url: String,
    val type: String,
    val category: String
)
