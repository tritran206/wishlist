package com.example.wishlist.data.model

import android.media.Image
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat
import java.util.*
import kotlin.contracts.Returns

@Entity
data class Product(
    @PrimaryKey @NonNull
    val productId : String,
    val productName : String?,
    val shortDescription : String?,
    val longDescription : String?,
    val price : String?,
    val productImage : String?,
    val reviewRating : Float?,
    val reviewCount : Int?,
    val inStock : Boolean?
)

data class ProductResponse(
    val products: List<Product>,
    val totalProducts: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val statusCode: Int
)
