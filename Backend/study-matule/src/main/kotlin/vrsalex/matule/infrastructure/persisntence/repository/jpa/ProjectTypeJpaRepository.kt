package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.domain.model.ProjectType
import vrsalex.matule.infrastructure.persisntence.entity.ProjectTypeEntity

interface ProjectTypeJpaRepository: JpaRepository<ProjectTypeEntity, Long> {
}