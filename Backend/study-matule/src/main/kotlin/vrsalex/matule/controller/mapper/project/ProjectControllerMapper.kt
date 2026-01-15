package vrsalex.matule.controller.mapper.project

import vrsalex.matule.api.response.project.ProjectCategoryResponse
import vrsalex.matule.api.response.project.ProjectTypeResponse
import vrsalex.matule.application.result.project.GetProjectTypesResult
import vrsalex.matule.domain.model.ProjectCategory
import vrsalex.matule.domain.model.ProjectType


fun ProjectType.toResponse(): ProjectTypeResponse = ProjectTypeResponse(
    id = this.id!!,
    name = this.name,
    description = this.description
)

fun ProjectCategory.toResponse(): ProjectCategoryResponse = ProjectCategoryResponse(
    id = this.id!!,
    name = this.name,
    description = this.description
)