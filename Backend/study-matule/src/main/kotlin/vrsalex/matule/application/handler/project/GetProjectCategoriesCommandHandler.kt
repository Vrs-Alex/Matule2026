package vrsalex.matule.application.handler.project

import org.springframework.stereotype.Component
import vrsalex.matule.application.result.project.GetProjectCategoriesResult
import vrsalex.matule.domain.port.repository.ProjectRepository

@Component
class GetProjectCategoriesCommandHandler(
    private val projectRepository: ProjectRepository
) {

    operator fun invoke(): GetProjectCategoriesResult {
        return GetProjectCategoriesResult(projectRepository.getAllProjectCategories())
    }

}