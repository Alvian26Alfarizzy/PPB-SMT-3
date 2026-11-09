package com.example.foodorder.data.repository

import com.example.foodorder.data.local.CartEntity
import com.example.foodorder.data.local.FoodDao
import com.example.foodorder.data.model.Food
import kotlinx.coroutines.flow.Flow

class FoodRepository(private val dao: FoodDao) {
    val cart: Flow<List<CartEntity>> = dao.observeCart()

    suspend fun add(food: Food) {
        val existing = dao.observeCart()
        // Read the current snapshot through the ViewModel's collected state before calling this method.
        dao.upsert(CartEntity(food.id, food.name, food.price, food.emoji, 1))
    }

    suspend fun setQuantity(item: CartEntity, quantity: Int) {
        if (quantity <= 0) dao.remove(item.foodId)
        else dao.upsert(item.copy(quantity = quantity))
    }

    suspend fun remove(foodId: Int) = dao.remove(foodId)
    suspend fun clear() = dao.clear()
}
