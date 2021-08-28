package com.example.week3.data.model

import java.text.NumberFormat
import java.util.*

data class Product(
    val name: String,
    val price: Double,
    val pictureUrl: String,
    val description: String
    ){

    fun getPrice(): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        return formatter.format(price)
//        return "$$price"
    }
}
