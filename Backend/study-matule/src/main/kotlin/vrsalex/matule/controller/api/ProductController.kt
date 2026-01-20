package vrsalex.matule.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.product.ProductResponse
import vrsalex.matule.application.handler.product.GetProductsCommandHandler
import vrsalex.matule.controller.mapper.product.toResponse

@RestController
class ProductController(
    private val getProductsCommandHandler: GetProductsCommandHandler
) {

    @GetMapping(ServerEndpoints.API.PRODUCTS_GET_ENDPOINT)
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        val products = getProductsCommandHandler().map { it.toResponse() }
        return ResponseEntity.ok(products)
    }

}