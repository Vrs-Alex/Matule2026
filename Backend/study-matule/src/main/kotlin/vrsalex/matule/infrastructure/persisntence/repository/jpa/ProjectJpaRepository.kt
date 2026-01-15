package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.domain.model.Project
import vrsalex.matule.infrastructure.persisntence.entity.ProjectEntity

interface ProjectJpaRepository: JpaRepository<ProjectEntity, Long> {

    fun findAllByUserId(userId: Long): List<ProjectEntity>

}