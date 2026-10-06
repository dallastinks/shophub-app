package com.example.shophub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.shophub.model.Product

@Composable
fun ProductCard(product: Product, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.storeName, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = product.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = product.originalPrice,
                    style = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.LineThrough)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = product.discountPrice,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}