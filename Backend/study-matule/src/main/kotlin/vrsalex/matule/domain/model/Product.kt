package vrsalex.matule.domain.model

data class Product(
    val id: Long?,
    val name: String,
    val subtitle: String,
    val price: String,
    val description: String,
    val category: Category
)
