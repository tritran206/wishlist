package com.example.wishlist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wishlist.data.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product: Product)

    @Query("SELECT * FROM Product")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getProduct(id: String): Product

    @Query("DELETE FROM Product WHERE id = :id")
    fun deleteProduct(id: String)
}