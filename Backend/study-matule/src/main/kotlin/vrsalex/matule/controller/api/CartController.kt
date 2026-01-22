package vrsalex.matule.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import vrsalex.matule.api.endpoints.ServerEndpoints
import vrsalex.matule.api.response.cart.UserCartItemResponse
import vrsalex.matule.application.command.cart.AddCartItemCommand
import vrsalex.matule.application.command.cart.ClearAllCartCommand
import vrsalex.matule.application.command.cart.GetCartItemsCommand
import vrsalex.matule.application.command.cart.RemoveCartItemCommand
import vrsalex.matule.controller.facade.CartFacade
import vrsalex.matule.controller.mapper.cart.toResponse
import vrsalex.matule.domain.model.User

@RestController
class CartController(
    private val cartFacade: CartFacade
) {

    @GetMapping(ServerEndpoints.API.USER_CART_GET_ENDPOINT)
    fun getUserCart(
        @AuthenticationPrincipal user: User
    ): ResponseEntity<List<UserCartItemResponse>> {
        val items = cartFacade.getCartItems(GetCartItemsCommand(user.id!!))
            .map { it.toResponse() }
        return ResponseEntity.ok(items)
    }

    @PostMapping(ServerEndpoints.API.USER_CART_ADD_ENDPOINT)
    fun addItemInCart(
        @AuthenticationPrincipal user: User,
        @RequestParam("product_id") productId: Long,
    ): ResponseEntity<UserCartItemResponse> {
        val item = cartFacade.addCartItem(AddCartItemCommand(user.id!!, productId))
        return ResponseEntity.ok(item.toResponse())
    }

    @PostMapping(ServerEndpoints.API.USER_CART_REMOVE_ALL_ENDPOINT)
    fun clearAllCart(
        @AuthenticationPrincipal user: User,
    ): ResponseEntity<Unit> {
        cartFacade.clearAllCart(ClearAllCartCommand(user.id!!))
        return ResponseEntity.ok().build()
    }

    @DeleteMapping(ServerEndpoints.API.USER_CART_REMOVE_ENDPOINT)
    fun deleteItemFromCart(
        @PathVariable("cart_item_id") productId: Long,
        @AuthenticationPrincipal user: User
    ): ResponseEntity<Unit> {
        cartFacade.removeCartItem(RemoveCartItemCommand(productId, user.id!!))
        return ResponseEntity.noContent().build()
    }

}