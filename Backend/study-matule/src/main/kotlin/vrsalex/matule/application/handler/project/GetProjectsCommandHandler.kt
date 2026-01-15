package vrsalex.matule.application.handler.project

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vrsalex.matule.api.response.project.ProjectCategoryResponse
import vrsalex.matule.api.response.project.ProjectResponse
import vrsalex.matule.api.response.project.ProjectTypeResponse
import vrsalex.matule.application.command.project.GetProjectsCommand
import vrsalex.matule.application.result.project.GetProjectsResult
import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.port.repository.ProjectRepository

@Component
class GetProjectsCommandHandler(
    private val projectRepository: ProjectRepository
){

    operator fun invoke(command: GetProjectsCommand): List<GetProjectsResult> {
        val res = projectRepository.getAllProjects(command.userId)
            .map { it.toGetProjectsResult() }
        return res
    }

    private fun Project.toGetProjectsResult(): GetProjectsResult {
        val formatter = java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

        return GetProjectsResult(
            id = this.id!!,
            name = this.name,
            startDate = this.startDate,
            endDate = this.endDate,
            url = this.url,
            type = ProjectTypeResponse(this.type.id!!, this.type.name, this.type.description),
            category = ProjectCategoryResponse(this.category.id!!, this.category.name, this.category.description),
            createdAt = this.createdAt.format(formatter),
            updatedAt = this.updatedAt.format(formatter)
        )
    }

}