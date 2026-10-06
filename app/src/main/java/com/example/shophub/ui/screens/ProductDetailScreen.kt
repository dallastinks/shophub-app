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
fun ProductDetailScreen(
    innerPadding: PaddingValues,
    productId: Int,
    onBackToList: () -> Unit
) {
    val product = ShopHubRepository.findProductById(productId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (product == null) {
            Text(
                text = "Produto não encontrado.",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(onClick = onBackToList) {
                Text("Voltar para produtos")
            }
            return
        }

        Text(
            text = "Detalhes do produto",
            style = MaterialTheme.typography.headlineSmall
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = "Preço: R$ ${product.price}")
                Text(text = "Categoria: ${ShopHubRepository.getCategoryName(product.categoryId)}")
                Text(text = "Descrição: ${product.description}")
                Text(
                    text = if (product.isFavorite) "Marcado como favorito" else "Ainda não favoritado"
                )
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