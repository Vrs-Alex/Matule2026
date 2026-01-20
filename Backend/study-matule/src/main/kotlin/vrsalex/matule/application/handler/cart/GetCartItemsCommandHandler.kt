package vrsalex.matule.application.handler.cart

import org.springframework.stereotype.Component
import vrsalex.matule.application.command.cart.GetCartItemsCommand
import vrsalex.matule.domain.model.CartItem
import vrsalex.matule.domain.port.repository.CartRepository

@Component
class GetCartItemsCommandHandler(
    private val cartRepository: CartRepository
) {
    operator fun invoke(command: GetCartItemsCommand): List<CartItem> =
        cartRepository.findAllByUserId(command.userId)

}