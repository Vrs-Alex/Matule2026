package vrsalex.matule.application.handler.cart

import org.springframework.stereotype.Component
import vrsalex.matule.application.command.cart.RemoveCartItemCommand
import vrsalex.matule.application.exception.exc.CartNotExistItemException
import vrsalex.matule.application.exception.exc.NoAccessException
import vrsalex.matule.domain.port.repository.CartRepository

@Component
class RemoveCartItemCommandHandler(
    private val cartRepository: CartRepository
) {

    operator fun invoke(command: RemoveCartItemCommand) {
        val item = cartRepository.findByCartId(command.cartId)
            ?: throw CartNotExistItemException("Элемента с id: ${command.cartId} в корзине нет")

        if (item.user.id != command.userId)
            throw NoAccessException("Нет доступа к чужой корзине")
        cartRepository.deleteById(command.cartId)
    }

}