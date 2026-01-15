package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.Project
import vrsalex.matule.domain.model.ProjectCategory
import vrsalex.matule.domain.model.ProjectType
import vrsalex.matule.infrastructure.persisntence.entity.ProjectCategoryEntity
import vrsalex.matule.infrastructure.persisntence.entity.ProjectEntity
import vrsalex.matule.infrastructure.persisntence.entity.ProjectTypeEntity


fun ProjectEntity.toDomain(): Project = Project(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    endDate = this.endDate,
    url = this.url,
    type = this.type!!.toDomain(),
    category = this.category!!.toDomain(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)


fun Project.toEntity(): ProjectEntity = ProjectEntity(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    endDate = this.endDate,
    url = this.url,
    type = this.type.toEntity(),
    category = this.category.toEntity(),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)


fun ProjectTypeEntity.toDomain(): ProjectType = ProjectType(
    id = this.id,
    name = this.name,
    description = this.description
)

fun ProjectType.toEntity(): ProjectTypeEntity = ProjectTypeEntity(
    id = this.id,
    name = this.name,
    description = this.description
)


fun ProjectCategoryEntity.toDomain(): ProjectCategory = ProjectCategory(
    id = this.id,
    name = this.name,
    description = this.description
)

fun ProjectCategory.toEntity(): ProjectCategoryEntity = ProjectCategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description
)