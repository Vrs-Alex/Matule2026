package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.stereotype.Repository
import vrsalex.matule.domain.model.Category
import vrsalex.matule.domain.port.repository.CategoryRepository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.repository.jpa.CategoryJpaRepository

@Repository
class CategoryRepositoryImpl(
    private val categoryJpaRepository: CategoryJpaRepository
): CategoryRepository {
    override fun getAllCategories(): List<Category> {
        return categoryJpaRepository.findAll().map {
            it.toDomain()
        }
    }
}