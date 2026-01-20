package vrsalex.matule.application.handler.cart

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import vrsalex.matule.application.command.cart.AddCartItemCommand
import vrsalex.matule.application.exception.exc.ProductNotExistException
import vrsalex.matule.application.exception.exc.UserNotExistException
import vrsalex.matule.domain.model.CartItem
import vrsalex.matule.domain.port.repository.CartRepository
import vrsalex.matule.domain.port.repository.ProductRepository
import vrsalex.matule.domain.port.repository.UserRepository

@Component
class AddCartItemCommandHandler(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    operator fun invoke(command: AddCartItemCommand): CartItem {
        val existingItem = cartRepository.findByUserIdAndProductId(command.userId, command.productId)

        val itemToSave = if (existingItem != null) {
            existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            val user = userRepository.findById(command.userId)
                ?: throw UserNotExistException("User not found with id: ${command.userId}")

            val product = productRepository.findById(command.productId)
                ?: throw ProductNotExistException("Product not found with id: ${command.productId}")

            CartItem(
                cartItemId = null,
                user = user,
                product = product,
                quantity = 1
            )
        }
        return cartRepository.save(itemToSave)
    }
}