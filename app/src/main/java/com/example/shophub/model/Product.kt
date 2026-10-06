data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val categoryId: Int,
    val description: String,
    val isFavorite: Boolean = false
)