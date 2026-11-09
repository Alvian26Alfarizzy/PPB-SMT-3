package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.local.CartEntity
import com.example.foodorder.ui.components.QuantitySelector
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartScreen(
    cart: List<CartEntity>,
    onQuantity: (CartEntity, Int) -> Unit,
    onRemove: (CartEntity) -> Unit,
    onCheckout: () -> Unit
) {
    val subtotal = cart.sumOf { it.price * it.quantity }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Keranjang", style = MaterialTheme.typography.headlineMedium) }
        if (cart.isEmpty()) item { Text("Keranjang masih kosong. Yuk pilih makanan!") }
        items(cart, key = { it.foodId }) { item ->
            Card {
                Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Text(item.emoji, style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text(item.name, style = MaterialTheme.typography.titleMedium)
                        Text(NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(item.price))
                        QuantitySelector(item.quantity, { onQuantity(item, -1) }, { onQuantity(item, 1) })
                    }
                    TextButton(onClick = { onRemove(item) }) { Text("Hapus") }
                }
            }
        }
        item {
            HorizontalDivider()
            Text("Subtotal: ${NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(subtotal)}", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Button(onClick = onCheckout, enabled = cart.isNotEmpty(), modifier = Modifier.fillMaxWidth()) { Text("Checkout") }
        }
    }
}
