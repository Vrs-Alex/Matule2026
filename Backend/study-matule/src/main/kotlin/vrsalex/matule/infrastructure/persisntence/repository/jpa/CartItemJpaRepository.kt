package vrsalex.matule.infrastructure.persisntence.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import vrsalex.matule.infrastructure.persisntence.entity.CartItemEntity

interface CartItemJpaRepository : JpaRepository<CartItemEntity, Long> {

    fun findAllByUserId(userId: Long): List<CartItemEntity>

    fun findByUserIdAndProductId(userId: Long, productId: Long): CartItemEntity?

}