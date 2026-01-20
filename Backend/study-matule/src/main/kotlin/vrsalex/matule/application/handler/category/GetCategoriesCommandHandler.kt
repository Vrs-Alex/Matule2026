package vrsalex.matule.application.handler.category

import org.springframework.stereotype.Component
import vrsalex.matule.application.result.category.CategoryResult
import vrsalex.matule.domain.model.Category
import vrsalex.matule.domain.port.repository.CategoryRepository

@Component
class GetCategoriesCommandHandler(
    private val categoryRepository: CategoryRepository
) {

    operator fun invoke(): CategoryResult {
        return categoryRepository.getAllCategories()
    }

}