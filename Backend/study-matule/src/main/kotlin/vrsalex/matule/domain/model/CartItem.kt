package vrsalex.matule.domain.model

data class CartItem(
    val cartItemId: Long?,
    val user: User,
    val product: Product,
    val quantity: Int
)
