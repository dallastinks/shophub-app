package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.model.CartItem
import com.example.shophub.model.Product
import com.example.shophub.model.Purchase

@Composable
fun MyOrdersScreen() {
    val purchases = listOf(
        Purchase(
            id = "#1042",
            date = "04/10/2026",
            items = listOf(CartItem(Product("2", "Notebook Gamer Acer Nitro 5", "R$ 5.500", "R$ 4.199", "23%", "Amazon", "Informática"), 1)),
            totalAmount = "R$ 4.199",
            status = "Entregue"
        ),
        Purchase(
            id = "#1018",
            date = "22/09/2026",
            items = listOf(CartItem(Product("3", "Fone Bluetooth Noise Cancelling", "R$ 399", "R$ 199", "50%", "Shopee", "Acessórios"), 1)),
            totalAmount = "R$ 199",
            status = "Em Trânsito"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Minhas Compras", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(purchases) { purchase ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Pedido ${purchase.id}", style = MaterialTheme.typography.titleMedium)
                            Text(text = purchase.status, color = MaterialTheme.colorScheme.primary)
                        }
                        Text(text = "Data: ${purchase.date}", style = MaterialTheme.typography.bodySmall)

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        purchase.items.forEach { item ->
                            Text(text = "${item.quantity}x ${item.product.title}")
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Total: ${purchase.totalAmount}",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}