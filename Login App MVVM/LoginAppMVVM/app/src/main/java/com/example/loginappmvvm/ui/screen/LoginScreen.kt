package com.example.loginappmvvm.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginappmvvm.viewmodel.LoginState
import com.example.loginappmvvm.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val loginState by viewModel.loginState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login App", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .imePadding(), // <--- Adds padding for the keyboard
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock Icon",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp),
                tint = Color(0xFF6200EE)
            )

            Text(
                text = "User Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Silakan masukkan username\ndan password",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = username,
                onValueChange = { 
                    username = it
                    viewModel.resetState()
                },
                label = { Text("Username") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Username Icon")
                },
                isError = loginState is LoginState.Error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { 
                    password = it
                    viewModel.resetState()
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
                },
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = if (loginState is LoginState.Error) 4.dp else if (loginState is LoginState.Success) 4.dp else 32.dp),
                shape = RoundedCornerShape(12.dp),
                isError = loginState is LoginState.Error,
                supportingText = {
                    if (loginState is LoginState.Error) {
                        Text(
                            text = "Usernama atau Password Salah. Coba lagi.",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else if (loginState is LoginState.Success) {
                        Text(
                            text = "Login Berhasil",
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                singleLine = true
            )

            if (loginState is LoginState.Error || loginState is LoginState.Success) {
                 Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = { viewModel.login(username, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                shape = RoundedCornerShape(25.dp)
            ) {
                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Banner for Login Result
            when (loginState) {
                is LoginState.Success -> {
                    // Removed success banner as we show success text below password
                }
                is LoginState.Error -> {
                   // Removed error banner as we show error text below password
                }
                else -> {
                    // Idle state
                }
            }
        }
    }
}
