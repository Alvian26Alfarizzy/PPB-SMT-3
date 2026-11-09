package com.example.foodorder.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.foodorder.ui.components.FoodBottomBar
import com.example.foodorder.ui.screens.*
import com.example.foodorder.viewmodel.FoodViewModel
import androidx.compose.ui.unit.dp

@Composable
fun AppNavigation(darkMode: Boolean, onDarkModeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val vm: FoodViewModel = viewModel()
    val cart by vm.cart.collectAsState()
    var selectedFoodId by remember { mutableIntStateOf(1) }
    val selectedFood = vm.foods.first { it.id == selectedFoodId }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: "home"

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "cart", "favorite", "profile")) {
                FoodBottomBar(currentRoute) { route -> navController.navigate(route) { launchSingleTop = true } }
            }
        }
    ) { padding ->
        NavHost(navController, startDestination = "home", modifier = Modifier.padding(padding)) {
            composable("home") {
                HomeScreen(vm.foods, { food -> selectedFoodId = food.id; navController.navigate("detail") }, vm::addFood)
            }
            composable("detail") {
                FoodDetailScreen(selectedFood, { navController.popBackStack() }, {
                    vm.addFood(selectedFood); navController.navigate("added")
                })
            }
            composable("added") {
                AddToCartScreen({ navController.navigate("cart") }, { navController.navigate("home") })
            }
            composable("cart") {
                CartScreen(cart, vm::changeQuantity, vm::removeFood) { navController.navigate("checkout") }
            }
            composable("checkout") {
                CheckoutScreen(cart, { navController.popBackStack() }) {
                    vm.clearCart(); navController.navigate("success") { popUpTo("home") }
                }
            }
            composable("success") {
                AddToCartScreen({ navController.navigate("home") }, { navController.navigate("home") })
            }
            composable("favorite") { SimplePage("Favorit", "Daftar makanan favorit akan tersedia di pengembangan berikutnya.") }
            composable("profile") {
                Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Profil", style = MaterialTheme.typography.headlineMedium)
                    Text("Pengaturan akun dan tampilan aplikasi.")
                    Button(onClick = { navController.navigate("theme") }) {
                        Text("Pengaturan Tema")
                    }
                }
            }
            composable("theme") { ThemeScreen(darkMode, onDarkModeChange) { navController.popBackStack() } }
        }
    }
}

@Composable
private fun SimplePage(title: String, description: String) {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(description)
    }
}
