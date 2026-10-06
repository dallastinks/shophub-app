package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.model.Product
import com.example.shophub.ui.components.ProductCard

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val sampleProducts = listOf(
        Product("1", "Smartphone Galaxy S23", "R$ 4.999", "R$ 3.299", "34%", "Kabum", "Eletrônicos"),
        Product("2", "Notebook Gamer Acer Nitro 5", "R$ 5.500", "R$ 4.199", "23%", "Amazon", "Informática"),
        Product("3", "Fone Bluetooth Noise Cancelling", "R$ 399", "R$ 199", "50%", "Shopee", "Acessórios")
    )

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Ofertas em Destaque", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(sampleProducts) { product ->
                ProductCard(product = product)
            }
        }
    }
}