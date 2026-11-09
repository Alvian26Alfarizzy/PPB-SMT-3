package com.example.foodorder.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon

@Composable
fun QuantitySelector(quantity: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        FilledTonalIconButton(onClick = onDecrease) {
            Icon(Icons.Default.Remove, contentDescription = "Kurangi")
        }
        Spacer(Modifier.width(8.dp))
        Text(quantity.toString(), style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.width(8.dp))
        FilledTonalIconButton(onClick = onIncrease) {
            Icon(Icons.Default.Add, contentDescription = "Tambah")
        }
    }
}
