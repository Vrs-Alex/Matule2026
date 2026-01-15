package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.domain.model.ProjectCategory
import vrsalex.matule.infrastructure.persisntence.entity.ProjectCategoryEntity

interface ProjectCategoryJpaRepository: JpaRepository<ProjectCategoryEntity, Long> {
}