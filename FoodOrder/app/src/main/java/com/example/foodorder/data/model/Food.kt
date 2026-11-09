package com.example.foodorder.data.model

data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    val category: String,
    val emoji: String
)

object FoodCatalog {
    val items = listOf(
        Food(1, "Nasi Goreng Spesial", "Nasi goreng dengan bumbu spesial, telur, dan sayuran segar.", 18000, "Makanan", "🍛"),
        Food(2, "Ayam Geprek", "Ayam crispy dengan sambal pilihan dan nasi hangat.", 20000, "Makanan", "🍗"),
        Food(3, "Mie Ayam", "Mie kenyal dengan ayam berbumbu dan sayuran.", 15000, "Makanan", "🍜"),
        Food(4, "Es Teh Manis", "Teh segar dengan es batu.", 5000, "Minuman", "🥤"),
        Food(5, "Jus Alpukat", "Jus alpukat creamy dan menyegarkan.", 12000, "Minuman", "🥑"),
        Food(6, "Kentang Goreng", "Kentang goreng renyah dengan saus.", 10000, "Camilan", "🍟")
    )
}
