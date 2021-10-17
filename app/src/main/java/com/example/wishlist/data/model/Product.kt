package com.example.wishlist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat
import java.util.*

@Entity
data class Product(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Double,
    val pictureUrl: String,
    val description: String
) {

    fun getFormatPrice(): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        return formatter.format(price)
    }

}
