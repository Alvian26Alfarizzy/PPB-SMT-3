package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddToCartScreen(onViewCart: () -> Unit, onContinue: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("✅", style = MaterialTheme.typography.displayLarge)
        Text("Berhasil Ditambahkan!", style = MaterialTheme.typography.headlineSmall)
        Text("Makanan telah ditambahkan ke keranjang.")
        Spacer(Modifier.height(24.dp))
        Button(onClick = onViewCart, modifier = Modifier.fillMaxWidth()) { Text("Lihat Keranjang") }
        OutlinedButton(onClick = onContinue, modifier = Modifier.fillMaxWidth()) { Text("Lanjut Belanja") }
    }
}
