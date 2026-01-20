package vrsalex.matule.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.category.CategoryResponse
import vrsalex.matule.application.handler.category.GetCategoriesCommandHandler
import vrsalex.matule.controller.mapper.category.toResponse

@RestController
class CategoryController(
    private val getCategories: GetCategoriesCommandHandler
) {

    @GetMapping(ServerEndpoints.API.CATEGORY_GET_ENDPOINT)
    fun getAllCategories(): ResponseEntity<List<CategoryResponse>> {
        val categories = getCategories().map { it.toResponse() }
        return ResponseEntity.ok(categories)
    }

}