package vrsalex.matule.application.handler.project

import org.springframework.stereotype.Component
import vrsalex.matule.api.response.project.ProjectCategoryResponse
import vrsalex.matule.api.response.project.ProjectTypeResponse
import vrsalex.matule.application.command.project.AddProjectCommand
import vrsalex.matule.application.exception.exc.ProjectNotExistException
import vrsalex.matule.application.result.project.GetProjectsResult
import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.port.repository.ProjectRepository
import java.time.LocalDateTime

@Component
class AddProjectCommandHandler(
    private val projectRepository: ProjectRepository
) {

    operator fun invoke(command: AddProjectCommand): GetProjectsResult {
        val type = projectRepository.getProjectTypeById(command.typeId)
            ?: throw ProjectNotExistException("Проект не найден")
        val category = projectRepository.getProjectCategoryById(command.categoryId)
            ?: throw ProjectNotExistException("Проект не найден")

        val project = Project(
            id = command.id, name = command.name, startDate = command.startDate,
            endDate = command.endDate, url = command.url, type = type,
            category = category, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())

        val projectFromDb = projectRepository.saveProject(project)
        val formatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val result = GetProjectsResult(
            id = projectFromDb.id!!,
            name = projectFromDb.name,
            startDate = projectFromDb.startDate,
            endDate = projectFromDb.endDate,
            url = projectFromDb.url,
            type = ProjectTypeResponse(projectFromDb.type.id!!, projectFromDb.type.name, projectFromDb.type.description),
            category = ProjectCategoryResponse(projectFromDb.category.id!!, projectFromDb.category.name, projectFromDb.category.description),
            createdAt = projectFromDb.createdAt.format(formatter),
            updatedAt = projectFromDb.updatedAt.format(formatter)
        )
        return result
    }

}