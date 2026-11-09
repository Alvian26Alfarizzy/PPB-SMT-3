package com.example.foodorder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodorder.data.local.CartEntity
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CheckoutScreen(cart: List<CartEntity>, onBack: () -> Unit, onSuccess: () -> Unit) {
    var address by remember { mutableStateOf("") }
    var payment by remember { mutableStateOf("Transfer Bank") }
    val total = cart.sumOf { it.price * it.quantity } + 5000
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        TextButton(onClick = onBack) { Text("← Kembali") }
        Text("Checkout", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(address, { address = it }, modifier = Modifier.fillMaxWidth(), label = { Text("Alamat pengiriman") })
        Text("Metode Pembayaran", style = MaterialTheme.typography.titleMedium)
        listOf("Transfer Bank", "E-Wallet", "QRIS").forEach {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(selected = payment == it, onClick = { payment = it })
                Text(it)
            }
        }
        Text("Ongkir: Rp5.000")
        Text("Total: ${NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(total)}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.weight(1f))
        Button(onClick = onSuccess, enabled = address.isNotBlank() && cart.isNotEmpty(), modifier = Modifier.fillMaxWidth()) { Text("Buat Pesanan") }
    }
}
