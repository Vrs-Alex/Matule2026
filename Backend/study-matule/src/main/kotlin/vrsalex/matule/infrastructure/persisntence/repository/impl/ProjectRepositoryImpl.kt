package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.stereotype.Repository
import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.model.ProjectCategory
import vrsalex.matule.domain.model.ProjectType
import vrsalex.matule.domain.port.repository.ProjectRepository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.mapper.toEntity
import vrsalex.matule.infrastructure.persisntence.repository.jpa.ProjectCategoryJpaRepository
import vrsalex.matule.infrastructure.persisntence.repository.jpa.ProjectJpaRepository
import vrsalex.matule.infrastructure.persisntence.repository.jpa.ProjectTypeJpaRepository

@Repository
class ProjectRepositoryImpl(
    private val projectJpaRepository: ProjectJpaRepository,
    private val projectTypeJpaRepository: ProjectTypeJpaRepository,
    private val projectCategoryJpaRepository: ProjectCategoryJpaRepository
): ProjectRepository {

    override fun getAllProjects(userId: Long): List<Project> {
        return projectJpaRepository.findAllByUserId(userId)
            .map { it.toDomain() }
    }

    override fun getProjectById(id: Long): Project? {
        return projectJpaRepository.findById(id)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun saveProject(project: Project): Project {
        val entity = project.toEntity()
        val savedEntity = projectJpaRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun deleteProjectById(id: Long) {
        projectJpaRepository.deleteById(id)
    }

    override fun getProjectTypeById(id: Long): ProjectType? {
        val entity = projectTypeJpaRepository.findById(id).orElse(null)
        return entity.toDomain()
    }

    override fun getProjectCategoryById(id: Long): ProjectCategory? {
        val entity = projectCategoryJpaRepository.findById(id).orElse(null)
        return entity.toDomain()
    }

    override fun getAllProjectTypes(): List<ProjectType> {
        val entities = projectTypeJpaRepository.findAll()
        return entities.map { it.toDomain() }
    }

    override fun getAllProjectCategories(): List<ProjectCategory> {
        val entities = projectCategoryJpaRepository.findAll()
        return entities.map { it.toDomain() }
    }
}