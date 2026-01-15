package vrsalex.matule.api.request.project

import kotlinx.serialization.Serializable
import vrsalex.matule.api.response.project.ProjectCategoryResponse
import vrsalex.matule.api.response.project.ProjectTypeResponse

@Serializable
data class AddProjectRequest(
    val id: Long?,
    val name: String,
    val startDate: String,
    val endDate: String,
    val url: String,
    val typeId: Long,
    val categoryId: Long
)
