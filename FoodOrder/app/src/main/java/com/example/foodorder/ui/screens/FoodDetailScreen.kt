package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.model.Food
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FoodDetailScreen(food: Food, onBack: () -> Unit, onAdd: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            TextButton(onClick = onBack) { Text("← Kembali") }
        }
        Spacer(Modifier.height(40.dp))
        Text(food.emoji, style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(20.dp))
        Text(food.name, style = MaterialTheme.typography.headlineSmall)
        Text(food.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))
        Text(NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(food.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.weight(1f))
        Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) { Text("Tambah ke Keranjang") }
    }
}
