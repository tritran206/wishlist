package com.example.wishlist.data.room

import android.content.Context
import androidx.room.*
import com.example.wishlist.data.model.CartItem
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review

@Database(entities = [Product::class, Review::class, CartItem::class], version = 5, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun reviewDao(): ReviewDao
    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }
}