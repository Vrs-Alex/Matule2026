package vrsalex.matule.infrastructure.persisntence.mapper

import vrsalex.matule.domain.model.Project

import vrsalex.matule.infrastructure.persisntence.entity.ProjectEntity


fun ProjectEntity.toDomain(): Project = Project(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    endDate = this.endDate,
    url = this.url,
    type = this.type!!,
    category = this.category!!,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)


fun Project.toEntity(): ProjectEntity = ProjectEntity(
    id = this.id,
    name = this.name,
    startDate = this.startDate,
    endDate = this.endDate,
    url = this.url,
    type = this.type,
    category = this.category,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)

//
//fun ProjectTypeEntity.toDomain(): ProjectType = ProjectType(
//    id = this.id,
//    name = this.name,
//    description = this.description
//)
//
//fun ProjectType.toEntity(): ProjectTypeEntity = ProjectTypeEntity(
//    id = this.id,
//    name = this.name,
//    description = this.description
//)
//
//
//fun ProjectCategoryEntity.toDomain(): ProjectCategory = ProjectCategory(
//    id = this.id,
//    name = this.name,
//    description = this.description
//)
//
//fun ProjectCategory.toEntity(): ProjectCategoryEntity = ProjectCategoryEntity(
//    id = this.id,
//    name = this.name,
//    description = this.description
//)