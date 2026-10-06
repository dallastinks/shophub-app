package com.example.shophub.model

data class Product(
    val id: String,
    val title: String,
    val originalPrice: String,
    val discountPrice: String,
    val discountPercent: String,
    val storeName: String,
    val category: String,
    val imageUrl: String = ""
)

data class CartItem(
    val product: Product,
    var quantity: Int
)

data class Purchase(
    val id: String,
    val date: String,
    val items: List<CartItem>,
    val totalAmount: String,
    val status: String
)