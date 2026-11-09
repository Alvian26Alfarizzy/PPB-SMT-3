package com.example.foodorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.foodorder.navigation.AppNavigation
import com.example.foodorder.ui.theme.FoodOrderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkMode by remember { mutableStateOf(false) }
            FoodOrderTheme(darkTheme = darkMode) {
                AppNavigation(darkMode, onDarkModeChange = { darkMode = it })
            }
        }
    }
}
