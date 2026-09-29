package com.example.quiz1studentmanager.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object AddStudent : Screen("add_student")
    object EditStudent : Screen("edit_student/{studentId}") {
        fun createRoute(studentId: String) = "edit_student/$studentId"
    }
}
