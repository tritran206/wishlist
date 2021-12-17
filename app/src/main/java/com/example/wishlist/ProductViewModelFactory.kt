package com.example.wishlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory (
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return getInstance(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        private var instance: ProductViewModel? = null

        fun getInstance(application: Application): ProductViewModel {
            if (instance == null) {
                instance = ProductViewModel(application)
            }
            return instance!!
        }
    }
}