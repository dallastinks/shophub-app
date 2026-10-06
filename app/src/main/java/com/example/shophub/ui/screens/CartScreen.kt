package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.model.CartItem
import com.example.shophub.model.Product

@Composable
fun CartScreen(onCheckoutSuccess: () -> Unit) {
    // Dados de exemplo simulando o carrinho
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem(Product("1", "Smartphone Galaxy S23", "R$ 4.999", "R$ 3.299", "34%", "Kabum", "Eletrônicos"), 1),
                CartItem(Product("3", "Fone Bluetooth Noise Cancelling", "R$ 399", "R$ 199", "50%", "Shopee", "Acessórios"), 2)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Meu Carrinho",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Seu carrinho está vazio 🛒",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = item.product.title, style = MaterialTheme.typography.titleMedium)
                                Text(text = item.product.discountPrice, color = MaterialTheme.colorScheme.primary)
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    if (item.quantity > 1) {
                                        cartItems = cartItems.map {
                                            if (it.product.id == item.product.id) it.copy(quantity = it.quantity - 1) else it
                                        }
                                    } else {
                                        cartItems = cartItems.filter { it.product.id != item.product.id }
                                    }
                                }) {
                                    Icon(if (item.quantity == 1) Icons.Default.Delete else Icons.Default.Remove, contentDescription = "Diminuir")
                                }

                                Text(text = "${item.quantity}", modifier = Modifier.padding(horizontal = 8.dp))

                                IconButton(onClick = {
                                    cartItems = cartItems.map {
                                        if (it.product.id == item.product.id) it.copy(quantity = it.quantity + 1) else it
                                    }
                                }) {
                                    Icon(Icons.Default.Add, contentDescription = "Aumentar")
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    cartItems = emptyList()
                    onCheckoutSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Finalizar Compra")
            }
        }
    }
}