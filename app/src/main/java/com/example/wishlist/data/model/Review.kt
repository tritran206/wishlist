package com.example.wishlist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    val productId: String,
    @PrimaryKey
    val id: String,
    val rating: Double,
    val user: String,
    val text: String
)