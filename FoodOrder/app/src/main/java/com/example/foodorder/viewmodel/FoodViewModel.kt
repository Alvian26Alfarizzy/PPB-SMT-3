package com.example.foodorder.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.foodorder.data.local.FoodDatabase
import com.example.foodorder.data.local.CartEntity
import com.example.foodorder.data.model.Food
import com.example.foodorder.data.model.FoodCatalog
import com.example.foodorder.data.repository.FoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val database = Room.databaseBuilder(
        application, FoodDatabase::class.java, "foodorder.db"
    ).build()
    private val repository = FoodRepository(database.foodDao())

    val foods = FoodCatalog.items
    val cart = repository.cart.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList()
    )

    fun addFood(food: Food) = viewModelScope.launch {
        val current = cart.value.firstOrNull { it.foodId == food.id }
        if (current == null) repository.add(food)
        else repository.setQuantity(current, current.quantity + 1)
    }

    fun changeQuantity(item: CartEntity, delta: Int) = viewModelScope.launch {
        repository.setQuantity(item, item.quantity + delta)
    }

    fun removeFood(item: CartEntity) = viewModelScope.launch {
        repository.remove(item.foodId)
    }

    fun clearCart() = viewModelScope.launch { repository.clear() }
}
