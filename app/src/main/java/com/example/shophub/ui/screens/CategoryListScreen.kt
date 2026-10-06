package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.data.ShopHubRepository
import com.example.shophub.model.Category

@Composable
fun CategoryListScreen(
    innerPadding: PaddingValues,
    onAddCategory: () -> Unit,
    onOpenDetails: (Int) -> Unit
) {
    val categories = ShopHubRepository.categories

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Lista de categorias",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = onAddCategory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar nova categoria")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories, key = { it.id }) { category ->
                CategoryCard(
                    category = category,
                    productCount = ShopHubRepository.countProductsByCategory(category.id),
                    onToggleFeatured = { ShopHubRepository.toggleFeatured(category.id) },
                    onRemove = { ShopHubRepository.removeCategory(category.id) },
                    onDetails = { onOpenDetails(category.id) }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    productCount: Int,
    onToggleFeatured: () -> Unit,
    onRemove: () -> Unit,
    onDetails: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = category.description)
            Text(text = "Produtos associados: $productCount")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Checkbox(
                        checked = category.featured,
                        onCheckedChange = { onToggleFeatured() }
                    )
                    Text(
                        text = "Destaque",
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Row {
                    IconButton(onClick = onDetails) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Detalhes"
                        )
                    }
                    IconButton(onClick = onRemove) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remover"
                        )
                    }
                }
            }
        }
    }
}