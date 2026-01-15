package vrsalex.matule.application.result.project

import vrsalex.matule.domain.model.ProjectType

data class GetProjectTypesResult(
    val projectTypes: List<ProjectType>
)