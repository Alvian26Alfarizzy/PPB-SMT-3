package com.example.quiz1studentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiz1studentmanager.ui.navigation.Screen
import com.example.quiz1studentmanager.ui.screens.AddEditScreen
import com.example.quiz1studentmanager.ui.screens.HomeScreen
import com.example.quiz1studentmanager.ui.screens.SplashScreen
import com.example.quiz1studentmanager.ui.theme.Quiz1StudentManagerTheme
import com.example.quiz1studentmanager.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Quiz1StudentManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentApp()
                }
            }
        }
    }
}

@Composable
fun StudentApp() {
    val navController = rememberNavController()
    val studentViewModel: StudentViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = studentViewModel)
        }
        composable(Screen.AddStudent.route) {
            AddEditScreen(navController = navController, viewModel = studentViewModel)
        }
        composable(
            route = Screen.EditStudent.route,
            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")
            AddEditScreen(
                navController = navController,
                viewModel = studentViewModel,
                studentId = studentId
            )
        }
    }
}
