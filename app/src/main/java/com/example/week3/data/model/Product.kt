package com.example.week3.data.model

data class Product(
    val name: String,
    val price: Double,
    val pictureUrl: String){

    fun getPrice(): String {
        return "$$price"
    }
}
