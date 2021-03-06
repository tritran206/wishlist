package com.example.wishlist.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "item_id")
    val productId: String
)



//data class Cart ( @PrimaryKey val id: Int = 1) {
//    @TypeConverters(StringTypeConverter::class)
//    var productIds: MutableList<String> = mutableListOf()
//
//    fun addToCart(productId: String) {
//        productIds.add(productId)
//    }
//
//    fun clearCart() {
//        productIds.clear()
//    }
//
//    fun removeFromCart(productId: String) {
//        productIds.remove(productId)
//    }
//}
//
