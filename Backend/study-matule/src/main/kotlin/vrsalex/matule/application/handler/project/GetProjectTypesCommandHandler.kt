package vrsalex.matule.application.handler.project

import org.springframework.stereotype.Component
import vrsalex.matule.application.result.project.GetProjectTypesResult
import vrsalex.matule.domain.port.repository.ProjectRepository
import vrsalex.matule.infrastructure.persisntence.entity.ProjectTypeEntity

@Component
class GetProjectTypesCommandHandler(
    private val projectRepository: ProjectRepository
) {
    operator fun invoke(): GetProjectTypesResult {
        return GetProjectTypesResult(projectRepository.getAllProjectTypes())
    }
}