package com.example.wishlist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wishlist.data.model.CartItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(cartItem: CartItem)

    @Query("SELECT * FROM CartItem")
    fun getCart(): LiveData<List<CartItem>>
}