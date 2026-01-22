package vrsalex.matule.controller.facade

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import vrsalex.matule.application.command.cart.AddCartItemCommand
import vrsalex.matule.application.command.cart.ClearAllCartCommand
import vrsalex.matule.application.command.cart.GetCartItemsCommand
import vrsalex.matule.application.command.cart.RemoveCartItemCommand
import vrsalex.matule.application.handler.cart.AddCartItemCommandHandler
import vrsalex.matule.application.handler.cart.ClearAllCartCommandHandler
import vrsalex.matule.application.handler.cart.GetCartItemsCommandHandler
import vrsalex.matule.application.handler.cart.RemoveCartItemCommandHandler

@Service
class CartFacade(
    private val getCartItemsCommandHandler: GetCartItemsCommandHandler,
    private val addCartItemCommandHandler: AddCartItemCommandHandler,
    private val removeCartItemCommandHandler: RemoveCartItemCommandHandler,
    private val clearAllCartCommandHandler: ClearAllCartCommandHandler
) {

    fun getCartItems(command: GetCartItemsCommand) = getCartItemsCommandHandler(command)

    fun addCartItem(command: AddCartItemCommand) = addCartItemCommandHandler(command)

    @Transactional
    fun removeCartItem(command: RemoveCartItemCommand) = removeCartItemCommandHandler(command)

    fun clearAllCart(command: ClearAllCartCommand) = clearAllCartCommandHandler(command)
}