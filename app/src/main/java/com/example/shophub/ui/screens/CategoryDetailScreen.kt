package com.example.shophub.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.data.ShopHubRepository

@Composable
fun CategoryDetailScreen(
    innerPadding: PaddingValues,
    categoryId: Int,
    onBackToList: () -> Unit
) {
    val category = ShopHubRepository.findCategoryById(categoryId)
    val relatedProducts = ShopHubRepository.products.filter { it.categoryId == categoryId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (category == null) {
            Text(
                text = "Categoria não encontrada.",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(onClick = onBackToList) {
                Text("Voltar para categorias")
            }
            return
        }

        Text(
            text = "Detalhes da categoria",
            style = MaterialTheme.typography.headlineSmall
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = "Descrição: ${category.description}")
                Text(
                    text = if (category.featured) "Categoria em destaque" else "Categoria comum"
                )
                Text(text = "Quantidade de produtos: ${relatedProducts.size}")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Produtos relacionados",
                    style = MaterialTheme.typography.titleMedium
                )

                if (relatedProducts.isEmpty()) {
                    Text("Nenhum produto cadastrado nesta categoria.")
                } else {
                    relatedProducts.forEach { product ->
                        Text("- ${product.name} | R$ ${product.price}")
                    }
                }
            }
        }

        Button(
            onClick = onBackToList,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar para a lista")
        }
    }
}