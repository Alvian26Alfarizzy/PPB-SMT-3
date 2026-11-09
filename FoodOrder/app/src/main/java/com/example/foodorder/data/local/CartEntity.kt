package com.example.foodorder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val foodId: Int,
    val name: String,
    val price: Int,
    val emoji: String,
    val quantity: Int
)
