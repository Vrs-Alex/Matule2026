package vrsalex.matule.controller.mapper.project

import vrsalex.matule.api.request.project.AddProjectRequest
import vrsalex.matule.application.command.project.AddProjectCommand

object AddProjectControllerMapper {
    
    fun toCommand(request: AddProjectRequest, userId: Long) = AddProjectCommand(
        id = request.id,
        name = request.name,
        startDate = request.startDate,
        endDate = request.endDate,
        url = request.url,
        type = request.type,
        category = request.category,
        userId = userId
    )

    
}