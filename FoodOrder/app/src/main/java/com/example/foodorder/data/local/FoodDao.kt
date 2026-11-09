package com.example.foodorder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM cart ORDER BY name")
    fun observeCart(): Flow<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: CartEntity)

    @Query("DELETE FROM cart WHERE foodId = :foodId")
    suspend fun remove(foodId: Int)

    @Query("DELETE FROM cart")
    suspend fun clear()
}
