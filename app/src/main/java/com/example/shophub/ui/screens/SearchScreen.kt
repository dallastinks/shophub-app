package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.model.Product
import com.example.shophub.ui.components.ProductCard

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }

    val allProducts = remember {
        listOf(
            Product("1", "Smartphone Galaxy S23", "R$ 4.999", "R$ 3.299", "34%", "Kabum", "Eletrônicos"),
            Product("2", "Notebook Gamer Acer Nitro 5", "R$ 5.500", "R$ 4.199", "23%", "Amazon", "Informática"),
            Product("3", "Fone Bluetooth Noise Cancelling", "R$ 399", "R$ 199", "50%", "Shopee", "Acessórios"),
            Product("4", "Monitor 144Hz IPS", "R$ 1.200", "R$ 899", "25%", "Pichau", "Informática"),
            Product("5", "Teclado Mecânico RGB", "R$ 350", "R$ 249", "28%", "Terabyte", "Periféricos")
        )
    }

    val filteredProducts = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.category.contains(searchQuery, ignoreCase = true) ||
                        it.storeName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar produtos ou lojas...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(filteredProducts) { product ->
                ProductCard(product = product)
            }
        }
    }
}