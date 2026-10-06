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
fun HomeScreen(
    innerPadding: PaddingValues,
    onOpenProducts: () -> Unit,
    onOpenCategories: () -> Unit,
    onOpenAddProduct: () -> Unit,
    onOpenAddCategory: () -> Unit,
    onOpenProfileSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Bem-vindo ao ShopHub",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Gerencie produtos e categorias com navegação real, listas, formulários e telas de detalhes.",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Resumo do app",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Produtos cadastrados: ${ShopHubRepository.products.size}")
                Text(text = "Categorias cadastradas: ${ShopHubRepository.categories.size}")
            }
        }

        Button(
            onClick = onOpenProducts,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver produtos")
        }

        Button(
            onClick = onOpenCategories,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver categorias")
        }

        Button(
            onClick = onOpenAddProduct,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar produto")
        }

        Button(
            onClick = onOpenAddCategory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar categoria")
        }

        Button(
            onClick = onOpenProfileSettings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Configurações do perfil")
        }
    }
}