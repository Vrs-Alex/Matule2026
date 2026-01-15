package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.model.ProjectCategory
import vrsalex.matule.domain.model.ProjectType


interface ProjectRepository {

    fun getAllProjects(userId: Long): List<Project>

    fun getProjectById(id: Long): Project?

    fun saveProject(project: Project): Project

    fun deleteProjectById(id: Long)

    fun getProjectTypeById(id: Long): ProjectType?

    fun getProjectCategoryById(id: Long): ProjectCategory?


    fun getAllProjectTypes(): List<ProjectType>

    fun getAllProjectCategories(): List<ProjectCategory>

}