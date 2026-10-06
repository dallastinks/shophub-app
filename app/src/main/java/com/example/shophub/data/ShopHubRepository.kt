package com.example.shophub.data

import androidx.compose.runtime.mutableStateListOf
import com.example.shophub.model.Category
import com.example.shophub.model.Product

object ShopHubRepository {
    val categories = mutableStateListOf(
        Category(
            id = 1,
            name = "Eletrônicos",
            description = "Produtos para casa, trabalho e entretenimento.",
            featured = true
        ),
        Category(
            id = 2,
            name = "Casa",
            description = "Itens úteis para organização e conforto.",
            featured = false
        ),
        Category(
            id = 3,
            name = "Estudos",
            description = "Materiais e acessórios para a rotina acadêmica.",
            featured = true
        )
    )

    val products = mutableStateListOf(
        Product(
            id = 1,
            name = "Headset Bluetooth",
            price = "199.90",
            categoryId = 1,
            description = "Headset sem fio com boa autonomia e microfone integrado.",
            isFavorite = true
        ),
        Product(
            id = 2,
            name = "Luminária de Mesa",
            price = "89.90",
            categoryId = 2,
            description = "Luminária compacta para leitura e estudos noturnos.",
            isFavorite = false
        ),
        Product(
            id = 3,
            name = "Caderno Inteligente",
            price = "45.50",
            categoryId = 3,
            description = "Caderno reutilizável para organização de conteúdos.",
            isFavorite = false
        )
    )

    private var nextProductId = products.maxOfOrNull { it.id }?.plus(1) ?: 1
    private var nextCategoryId = categories.maxOfOrNull { it.id }?.plus(1) ?: 1

    fun addProduct(
        name: String,
        price: String,
        categoryId: Int,
        description: String
    ) {
        products.add(
            Product(
                id = nextProductId++,
                name = name,
                price = price,
                categoryId = categoryId,
                description = description
            )
        )
    }

    fun removeProduct(id: Int) {
        products.removeAll { it.id == id }
    }

    fun toggleFavorite(id: Int) {
        val index = products.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = products[index]
            products[index] = current.copy(isFavorite = !current.isFavorite)
        }
    }

    fun findProductById(id: Int): Product? = products.find { it.id == id }

    fun addCategory(
        name: String,
        description: String
    ) {
        categories.add(
            Category(
                id = nextCategoryId++,
                name = name,
                description = description
            )
        )
    }

    fun removeCategory(id: Int) {
        categories.removeAll { it.id == id }
    }

    fun toggleFeatured(id: Int) {
        val index = categories.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = categories[index]
            categories[index] = current.copy(featured = !current.featured)
        }
    }

    fun findCategoryById(id: Int): Category? = categories.find { it.id == id }

    fun getCategoryName(categoryId: Int): String {
        return categories.find { it.id == categoryId }?.name ?: "Sem categoria"
    }

    fun countProductsByCategory(categoryId: Int): Int {
        return products.count { it.categoryId == categoryId }
    }
}