package vrsalex.matule.application.handler.cart

import org.springframework.stereotype.Component
import vrsalex.matule.application.command.cart.ClearAllCartCommand
import vrsalex.matule.domain.port.repository.CartRepository

@Component
class ClearAllCartCommandHandler(
    private val cartRepository: CartRepository
) {

    operator fun invoke(command: ClearAllCartCommand){
        cartRepository.clearAll()
    }

}
