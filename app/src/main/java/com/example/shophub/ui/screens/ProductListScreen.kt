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
import com.example.shophub.model.Product

@Composable
fun ProductListScreen(
    innerPadding: PaddingValues,
    onAddProduct: () -> Unit,
    onOpenDetails: (Int) -> Unit
) {
    val products = ShopHubRepository.products

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Lista de produtos",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = onAddProduct,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar novo produto")
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    categoryName = ShopHubRepository.getCategoryName(product.categoryId),
                    onToggleFavorite = { ShopHubRepository.toggleFavorite(product.id) },
                    onRemove = { ShopHubRepository.removeProduct(product.id) },
                    onDetails = { onOpenDetails(product.id) }
                )
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    categoryName: String,
    onToggleFavorite: () -> Unit,
    onRemove: () -> Unit,
    onDetails: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = "Preço: R$ ${product.price}")
            Text(text = "Categoria: $categoryName")
            Text(text = product.description)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Checkbox(
                        checked = product.isFavorite,
                        onCheckedChange = { onToggleFavorite() }
                    )
                    Text(
                        text = "Favorito",
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