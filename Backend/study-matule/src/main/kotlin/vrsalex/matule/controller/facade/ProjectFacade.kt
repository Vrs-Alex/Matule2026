package vrsalex.matule.controller.facade

import org.springframework.stereotype.Service
import vrsalex.matule.application.command.project.AddProjectCommand
import vrsalex.matule.application.command.project.GetProjectsCommand
import vrsalex.matule.application.handler.project.AddProjectCommandHandler
import vrsalex.matule.application.handler.project.GetProjectCategoriesCommandHandler
import vrsalex.matule.application.handler.project.GetProjectTypesCommandHandler
import vrsalex.matule.application.handler.project.GetProjectsCommandHandler

@Service
class ProjectFacade(
    private val getProjectsCommandHandler: GetProjectsCommandHandler,
    private val addProjectCommandHandler: AddProjectCommandHandler,
    private val getProjectTypesCommandHandler: GetProjectTypesCommandHandler,
    private val getProjectCategoriesCommandHandler: GetProjectCategoriesCommandHandler
) {

    fun getProjects(command: GetProjectsCommand) = getProjectsCommandHandler(command)

    fun addProject(command: AddProjectCommand) = addProjectCommandHandler(command)

    fun getProjectTypes() = getProjectTypesCommandHandler()

    fun getProjectCategories() = getProjectCategoriesCommandHandler()

}