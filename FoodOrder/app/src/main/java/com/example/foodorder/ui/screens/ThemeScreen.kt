package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemeScreen(darkMode: Boolean, onDarkModeChange: (Boolean) -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        TextButton(onClick = onBack) { Text("← Kembali") }
        Text("Tema Aplikasi", style = MaterialTheme.typography.headlineMedium)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(if (darkMode) "Dark Mode (Gelap)" else "Light Mode (Terang)")
            Switch(checked = darkMode, onCheckedChange = onDarkModeChange)
        }
        Text("Tema mengikuti pilihan pengguna dan diterapkan ke seluruh halaman.")
    }
}
