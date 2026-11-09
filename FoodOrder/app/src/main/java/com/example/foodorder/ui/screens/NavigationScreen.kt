package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextButton(onClick = onBack) { Text("← Kembali") }
        Text("Navigasi Halaman", style = MaterialTheme.typography.headlineMedium)
        Text("Gunakan navigasi bawah untuk berpindah antara Beranda, Pesanan, Favorit, dan Profil.")
        Text("FoodOrder • Material Design 3")
    }
}
