package com.example.wishlist.data.model

data class Review(
    val productId: String,
    val id: String,
    val rating: Double,
    val user: String,
    val text: String
)