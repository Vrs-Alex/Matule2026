package vrsalex.matule.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.request.project.AddProjectRequest
import vrsalex.matule.api.response.project.ProjectCategoryResponse
import vrsalex.matule.api.response.project.ProjectResponse
import vrsalex.matule.api.response.project.ProjectTypeResponse
import vrsalex.matule.application.command.project.AddProjectCommand
import vrsalex.matule.application.command.project.GetProjectsCommand
import vrsalex.matule.controller.facade.ProfileFacade
import vrsalex.matule.controller.facade.ProjectFacade
import vrsalex.matule.controller.mapper.project.AddProjectControllerMapper
import vrsalex.matule.controller.mapper.project.toResponse
import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.model.User

@RestController
class ProjectController(
    private val projectFacade: ProjectFacade
) {

    @GetMapping(ServerEndpoints.API.USER_PROJECTS_GET_ENDPOINT)
    fun getProjects(
        @AuthenticationPrincipal user: User
    ): ResponseEntity<List<ProjectResponse>> {
        val projects = projectFacade.getProjects(GetProjectsCommand(user.id!!))
        return ResponseEntity.ok(projects)
    }

    @PostMapping(ServerEndpoints.API.USER_PROJECTS_ADD_ENDPOINT)
    fun addProject(
        @AuthenticationPrincipal user: User,
        @RequestBody request: AddProjectRequest
    ): ResponseEntity<ProjectResponse> {
        val project = projectFacade.addProject(AddProjectControllerMapper.toCommand(request, user.id!!))
        return ResponseEntity.ok(project)
    }

    @PostMapping(ServerEndpoints.API.USER_PROJECTS_REMOVE_ENDPOINT)
    fun removeProject(
        @AuthenticationPrincipal user: User,
        @RequestParam("projectId") projectId: Long
    ){

    }

    @GetMapping(ServerEndpoints.API.USER_PROJECT_TYPE_GET_ENDPOINT)
    fun getProjectType(): ResponseEntity<List<ProjectTypeResponse>> {
        val projectTypes = projectFacade.getProjectTypes()
        val res = projectTypes.projectTypes.map { it.toResponse() }
        return ResponseEntity.ok(res)
    }

    @GetMapping(ServerEndpoints.API.USER_PROJECT_CATEGORY_GET_ENDPOINT)
    fun getProjectCategory(): ResponseEntity<List<ProjectCategoryResponse>> {
        val projectCategories = projectFacade.getProjectCategories()
        val res = projectCategories.projectCategories.map { it.toResponse() }
        return ResponseEntity.ok(res)
    }


}