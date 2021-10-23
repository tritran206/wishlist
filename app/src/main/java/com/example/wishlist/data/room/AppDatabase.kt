package com.example.wishlist.data.room

import android.content.Context
import androidx.room.*
import com.example.wishlist.StringTypeConverter
import com.example.wishlist.data.model.Cart
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review

@Database(entities = [Product::class, Review::class, Cart::class], version = 1, exportSchema = false)
@TypeConverters(StringTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    // TODO: Change to val if doesn't work
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