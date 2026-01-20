package vrsalex.matule.domain.port.repository

import vrsalex.matule.domain.model.Product

interface ProductRepository {

    fun findAll(): List<Product>

    fun findById(id: Long): Product?

}