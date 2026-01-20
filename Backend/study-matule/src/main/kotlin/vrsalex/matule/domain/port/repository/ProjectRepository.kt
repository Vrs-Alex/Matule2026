package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.Project



interface ProjectRepository {

    fun getAllProjects(userId: Long): List<Project>

    fun getProjectById(id: Long): Project?

    fun saveProject(project: Project): Project

    fun deleteProjectById(id: Long)


}