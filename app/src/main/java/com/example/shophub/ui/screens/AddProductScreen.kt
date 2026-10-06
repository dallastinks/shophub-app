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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shophub.data.ShopHubRepository

@Composable
fun AddProductScreen(
    innerPadding: PaddingValues,
    onSave: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var categoryIdText by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val categories = ShopHubRepository.categories

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Adicionar produto",
            style = MaterialTheme.typography.headlineSmall
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Categorias disponíveis:")
                categories.forEach { category ->
                    Text("${category.id} - ${category.name}")
                }
            }
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do produto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categoryIdText,
            onValueChange = { categoryIdText = it },
            label = { Text("ID da categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                val categoryId = categoryIdText.toIntOrNull()
                val categoryExists = categories.any { it.id == categoryId }

                when {
                    name.isBlank() || price.isBlank() || description.isBlank() || categoryId == null -> {
                        errorMessage = "Preencha todos os campos corretamente."
                    }
                    !categoryExists -> {
                        errorMessage = "Informe um ID de categoria válido."
                    }
                    else -> {
                        ShopHubRepository.addProduct(
                            name = name,
                            price = price,
                            categoryId = categoryId,
                            description = description
                        )
                        onSave()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar produto")
        }
    }
}