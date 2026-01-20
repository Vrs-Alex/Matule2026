package vrsalex.matule.application.command.cart

data class RemoveCartItemCommand(
    val cartId: Long,
    val userId: Long
)
