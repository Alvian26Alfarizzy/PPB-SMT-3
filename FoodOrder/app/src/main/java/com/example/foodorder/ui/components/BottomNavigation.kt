package com.example.foodorder.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun FoodBottomBar(selected: String, onSelect: (String) -> Unit) {
    NavigationBar {
        listOf(
            Triple("home", "Beranda", Icons.Default.Home),
            Triple("cart", "Pesanan", Icons.Default.ReceiptLong),
            Triple("favorite", "Favorit", Icons.Default.Favorite),
            Triple("profile", "Profil", Icons.Default.Person)
        ).forEach { (route, label, icon) ->
            NavigationBarItem(
                selected = selected == route,
                onClick = { onSelect(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}
