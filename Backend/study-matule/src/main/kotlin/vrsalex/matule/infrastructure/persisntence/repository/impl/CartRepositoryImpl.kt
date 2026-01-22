package vrsalex.matule.infrastructure.persisntence.repository.impl

import org.springframework.stereotype.Repository
import vrsalex.matule.domain.model.CartItem
import vrsalex.matule.domain.port.repository.CartRepository
import vrsalex.matule.infrastructure.persisntence.mapper.toDomain
import vrsalex.matule.infrastructure.persisntence.mapper.toEntity
import vrsalex.matule.infrastructure.persisntence.repository.jpa.CartItemJpaRepository
import vrsalex.matule.infrastructure.persisntence.repository.jpa.UserJpaRepository

@Repository
class CartRepositoryImpl(
    private val cartJpaRepository: CartItemJpaRepository
): CartRepository {
    override fun findAllByUserId(userId: Long): List<CartItem> =
        cartJpaRepository.findAllByUserId(userId).map { it.toDomain() }

    override fun save(cartItem: CartItem): CartItem =
        cartJpaRepository.save(cartItem.toEntity()).toDomain()

    override fun deleteById(cartItemId: Long) =
        cartJpaRepository.deleteById(cartItemId)


    override fun findByUserIdAndProductId(
        userId: Long,
        productId: Long
    ): CartItem? =
        cartJpaRepository.findByUserIdAndProductId(userId, productId)?.toDomain()

    override fun findByCartId(cartId: Long): CartItem? {
        val entity = cartJpaRepository.findById(cartId).orElse(null)
        return entity?.toDomain()
    }

    override fun clearAll() {
        cartJpaRepository.deleteAll()
    }


}