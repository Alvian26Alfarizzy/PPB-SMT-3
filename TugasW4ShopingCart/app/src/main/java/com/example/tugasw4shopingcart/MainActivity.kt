package com.example.tugasw4shopingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugasw4shopingcart.ui.theme.TugasW4ShopingCartTheme
import java.text.NumberFormat
import java.util.Locale

// 1. Data Class untuk Produk
data class CartItem(
    val id: String,
    val name: String,
    val price: Int,
    val quantity: Int = 1,
    val imageResId: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasW4ShopingCartTheme {
                ShoppingCartScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen() {
    // 2. State Management (Menyimpan daftar produk yang reaktif terhadap perubahan)
    val cartItems = remember {
        mutableStateListOf(
            CartItem("1", "Kaos Polos", 75000, 1, imageResId = R.drawable.kaos_polos),
            CartItem("2", "Sepatu Sneakers", 250000, 1, imageResId = R.drawable.sepatu)
        )
    }

    // Menghitung Total Harga otomatis setiap kali state cartItems berubah
    val totalPrice = cartItems.sumOf { it.price * it.quantity }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Keranjang Saya", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF90DA62) // Warna Hijau
                )
            )
        },
        bottomBar = {
            BottomCartSection(
                totalPrice = totalPrice,
                onClearCart = { cartItems.clear() }
            )
        }
    ) { innerPadding ->
        // 3. Daftar Produk
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(cartItems) { index, item ->
                CartItemCard(
                    item = item,
                    onAdd = {
                        cartItems[index] = item.copy(quantity = item.quantity + 1)
                    },
                    onMin = {
                        if (item.quantity > 1) {
                            cartItems[index] = item.copy(quantity = item.quantity - 1)
                        }
                    },
                    onDelete = {
                        cartItems.removeAt(index)
                    }
                )
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: CartItem,
    onAdd: () -> Unit,
    onMin: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFEEEEEE))
            )
            // --------------------------------------------------------

            Spacer(modifier = Modifier.width(12.dp))

            // Detail Produk & Tombol
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = formatRupiah(item.price), color = Color.Gray, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Tombol Minus
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color(0xFFEEEEEE), RoundedCornerShape(4.dp))
                            .clickable{onMin()},
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Min", modifier = Modifier.size(16.dp))
                    }

                    Text(
                        text = item.quantity.toString(),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    // Tombol Plus
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color(0xFFE3F2FD), RoundedCornerShape(4.dp))
                            .clickable{onAdd()},
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color(0xFF1E88E5), modifier = Modifier.size(16.dp))
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Tombol Hapus
                    IconButton(onClick = onDelete, modifier = Modifier.size(32.dp)) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomCartSection(totalPrice: Int, onClearCart: () -> Unit) {
    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Total Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = formatRupiah(totalPrice), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            // Button Checkout
            Button(
                onClick = { /* TODO: Checkout logic */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90DA62)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Checkout")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Button Lanjut Belanja
            OutlinedButton(
                onClick = { /* TODO: Lanjut Belanja logic */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1E88E5))
            ) {
                Text("Lanjut Belanja")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Button Hapus Semua
            Button(
                onClick = onClearCart,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Hapus Semua")
            }
        }
    }
}

// Helper untuk format Rupiah
fun formatRupiah(number: Int): String {
    val localeID = Locale("in", "ID")
    val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
    return formatRupiah.format(number).replace(",00", "")
}

@Preview(showBackground = true)
@Composable
fun ShoppingCartPreview() {
    TugasW4ShopingCartTheme {
        ShoppingCartScreen()
    }
}