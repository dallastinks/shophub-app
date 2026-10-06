package com.example.shophub.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.model.Product
import com.example.shophub.ui.components.ProductCard

@Composable
fun CategoriesScreen() {
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val categories = listOf("Eletrônicos", "Informática", "Acessórios", "Periféricos", "Casa & Estilo")

    val allProducts = listOf(
        Product("1", "Smartphone Galaxy S23", "R$ 4.999", "R$ 3.299", "34%", "Kabum", "Eletrônicos"),
        Product("2", "Notebook Gamer Acer Nitro 5", "R$ 5.500", "R$ 4.199", "23%", "Amazon", "Informática"),
        Product("3", "Fone Bluetooth Noise Cancelling", "R$ 399", "R$ 199", "50%", "Shopee", "Acessórios"),
        Product("4", "Monitor 144Hz IPS", "R$ 1.200", "R$ 899", "25%", "Pichau", "Informática"),
        Product("5", "Teclado Mecânico RGB", "R$ 350", "R$ 249", "28%", "Terabyte", "Periféricos")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (selectedCategory == null) {
            Text(text = "Categorias", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clickable { selectedCategory = category },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { selectedCategory = null }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = selectedCategory!!, style = MaterialTheme.typography.headlineSmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            val filtered = allProducts.filter { it.category.equals(selectedCategory, ignoreCase = true) }

            if (filtered.isEmpty()) {
                Text("Nenhum produto encontrado nesta categoria.")
            } else {
                LazyColumn {
                    items(filtered) { product ->
                        ProductCard(product = product)
                    }
                }
            }
        }
    }
}