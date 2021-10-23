package com.example.wishlist.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wishlist.data.model.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(cart: Cart)

    @Query("SELECT * FROM Cart WHERE id= 1")
    fun getCart(): Cart
}