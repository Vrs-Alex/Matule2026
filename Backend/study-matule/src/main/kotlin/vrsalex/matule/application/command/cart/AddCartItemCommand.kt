package vrsalex.matule.application.command.cart

data class AddCartItemCommand(
    val userId: Long,
    val productId: Long
)
