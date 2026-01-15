package vrsalex.matule.application.result.project

import vrsalex.matule.domain.model.ProjectCategory

data class GetProjectCategoriesResult(
    val projectCategories: List<ProjectCategory>
)
