package com.example.tugaskalkulatorw7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                KalkulatorModernScreen()
            }
        }
    }
}

// 1. Sintaks Deklaratif dengan @Composable
@Composable
fun KalkulatorModernScreen() {
    // 2. State Management: remember & mutableStateOf
    var angka1 by remember { mutableStateOf("") }
    var angka2 by remember { mutableStateOf("") }
    var hasil by remember { mutableStateOf("0") }
    var operatorAktif by remember { mutableStateOf("") }

    // Palet Warna Modern (Slate & Indigo Theme)
    val bgScreen = Color(0xFFF1F5F9)       // Abu-abu kebiruan sangat muda
    val cardBg = Color(0xFFFFFFFF)         // Putih bersih
    val textPrimary = Color(0xFF0F172A)    // Hitam slate tua
    val textSecondary = Color(0xFF64748B)  // Abu-abu medium
    val accentIndigo = Color(0xFF4F46E5)   // Indigo modern
    val btnOperatorBg = Color(0xFFEEF2FF)  // Latar tombol operator (Soft Indigo)
    val btnClearBg = Color(0xFFFFE4E6)     // Latar tombol clear (Soft Rose)
    val btnClearText = Color(0xFFE11D48)   // Teks tombol clear (Crimson)
    val fieldBg = Color(0xFFF8FAFC)        // Latar input field

    fun hitung(simbol: String, operasi: (Double, Double) -> Double) {
        operatorAktif = simbol
        val n1 = angka1.replace(",", ".").toDoubleOrNull()
        val n2 = angka2.replace(",", ".").toDoubleOrNull()

        if (n1 != null && n2 != null) {
            val res = operasi(n1, n2)
            hasil = if (res % 1.0 == 0.0) {
                res.toLong().toString()
            } else {
                String.format("%.2f", res).trimEnd('0').trimEnd('.').trimEnd(',')
            }
        } else {
            hasil = "Input belum lengkap"
        }
    }

    // 3. Box View: Wadah utama (container) untuk menyusun elemen UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgScreen)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // Card Pembungkus agar tampilan rapi dan modern
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            color = cardBg,
            shadowElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header Aplikasi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Kalkulator",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = textPrimary
                        )
                        Text(
                            text = "Jetpack Compose Edition",
                            fontSize = 13.sp,
                            color = textSecondary,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Badge indikator operator yang sedang dipilih
                    if (operatorAktif.isNotEmpty()) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = btnOperatorBg
                        ) {
                            Text(
                                text = "Mode: $operatorAktif",
                                color = accentIndigo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // 4. Struktur Input TextField (Tiga TextField: 2 Input & 1 Tampilan Hasil)
                // TextField 1: Input Angka Pertama
                OutlinedTextField(
                    value = angka1,
                    onValueChange = { angka1 = it },
                    label = { Text("Angka Pertama") },
                    placeholder = { Text("0") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentIndigo,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedContainerColor = fieldBg,
                        unfocusedContainerColor = fieldBg,
                        focusedLabelColor = accentIndigo
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // TextField 2: Input Angka Kedua
                OutlinedTextField(
                    value = angka2,
                    onValueChange = { angka2 = it },
                    label = { Text("Angka Kedua") },
                    placeholder = { Text("0") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentIndigo,
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedContainerColor = fieldBg,
                        unfocusedContainerColor = fieldBg,
                        focusedLabelColor = accentIndigo
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = textPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // TextField 3: Tampilan Hasil (Didesain menyerupai layar display kalkulator)
                TextField(
                    value = hasil,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(
                            text = "Hasil Perhitungan",
                            color = textSecondary,
                            fontSize = 12.sp
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF1F5F9),
                        unfocusedContainerColor = Color(0xFFF1F5F9),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = accentIndigo,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 5. Tombol Aritmatika & Fungsi Clear (Lima tombol dengan listener onClick)
                // Baris 1: Keempat Tombol Operasi sejajar rapi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Tombol Tambah (+)
                    Button(
                        onClick = { hitung("+") { a, b -> a + b } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (operatorAktif == "+") accentIndigo else btnOperatorBg,
                            contentColor = if (operatorAktif == "+") Color.White else accentIndigo
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    // Tombol Kurang (−)
                    Button(
                        onClick = { hitung("−") { a, b -> a - b } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (operatorAktif == "−") accentIndigo else btnOperatorBg,
                            contentColor = if (operatorAktif == "−") Color.White else accentIndigo
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text("−", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    // Tombol Kali (×)
                    Button(
                        onClick = { hitung("×") { a, b -> a * b } },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (operatorAktif == "×") accentIndigo else btnOperatorBg,
                            contentColor = if (operatorAktif == "×") Color.White else accentIndigo
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text("×", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }

                    // Tombol Bagi (÷)
                    Button(
                        onClick = {
                            val n2 = angka2.replace(",", ".").toDoubleOrNull()
                            if (n2 == 0.0) {
                                operatorAktif = "÷"
                                hasil = "Tidak bisa ÷ 0"
                            } else {
                                hitung("÷") { a, b -> a / b }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (operatorAktif == "÷") accentIndigo else btnOperatorBg,
                            contentColor = if (operatorAktif == "÷") Color.White else accentIndigo
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text("÷", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Baris 2: Tombol Clear (Reset Input)
                Button(
                    onClick = {
                        angka1 = ""
                        angka2 = ""
                        hasil = "0"
                        operatorAktif = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = btnClearBg,
                        contentColor = btnClearText
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    Text(
                        text = "CLEAR",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KalkulatorModernPreview() {
    MaterialTheme {
        KalkulatorModernScreen()
    }
}