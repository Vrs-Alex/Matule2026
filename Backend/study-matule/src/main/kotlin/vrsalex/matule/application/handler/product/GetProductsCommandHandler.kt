package vrsalex.matule.application.handler.product

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vrsalex.matule.application.result.product.ProductResult
import vrsalex.matule.domain.model.Product
import vrsalex.matule.domain.port.repository.ProductRepository

@Component
class GetProductsCommandHandler(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): ProductResult {
        return productRepository.findAll()
    }

}