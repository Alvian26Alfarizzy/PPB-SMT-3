package com.example.loginappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginappmvvm.data.local.database.AppDatabase
import com.example.loginappmvvm.data.repository.UserRepository
import com.example.loginappmvvm.ui.screen.LoginScreen
import com.example.loginappmvvm.ui.theme.LoginAppMVVMTheme
import com.example.loginappmvvm.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAppMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val database = AppDatabase.getDatabase(context)
                    val repository = UserRepository(database.userDao())
                    
                    val loginViewModel: LoginViewModel = viewModel(
                        factory = LoginViewModel.Factory(repository)
                    )
                    
                    LoginScreen(viewModel = loginViewModel)
                }
            }
        }
    }
}
