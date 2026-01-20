package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import vrsalex.matule.domain.model.Product
import vrsalex.matule.domain.port.repository.ProductRepository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.repository.jpa.ProductJpaRepository

@Repository
class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
): ProductRepository {
    override fun findAll(): List<Product> {
        return productJpaRepository.findAll().map { it.toDomain() }
    }

    override fun findById(id: Long): Product? {
        return productJpaRepository.findByIdOrNull(id)?.toDomain()
    }
}