package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.model.Food
import com.example.foodorder.ui.components.FoodCard

@Composable
fun HomeScreen(
    foodList: List<Food>,
    onFoodClick: (Food) -> Unit,
    onAddClick: (Food) -> Unit
) {
    var search by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Semua") }
    val categories = listOf("Semua", "Makanan", "Minuman", "Camilan")
    val filtered = foodList.filter {
        (category == "Semua" || it.category == category) &&
            it.name.contains(search, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("FoodOrder", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(12.dp))
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)) {
                Column(Modifier.fillMaxWidth().padding(18.dp)) {
                    Text("Halo, Selamat Datang 👋", style = MaterialTheme.typography.titleLarge)
                    Text("Pesan makanan favoritmu!")
                }
            }
        }
        item {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Cari makanan...") },
                singleLine = true
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                categories.forEach {
                    FilterChip(
                        selected = category == it,
                        onClick = { category = it },
                        label = { Text(it) }
                    )
                }
            }
        }
        item { Text("Rekomendasi", style = MaterialTheme.typography.titleLarge) }
        items(filtered, key = { it.id }) { food ->
            FoodCard(food, onClick = { onFoodClick(food) }, onAddClick = { onAddClick(food) })
        }
        if (filtered.isEmpty()) item { Text("Makanan tidak ditemukan.") }
    }
}
