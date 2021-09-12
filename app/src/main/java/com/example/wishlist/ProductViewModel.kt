package com.example.wishlist

import androidx.lifecycle.ViewModel
import com.example.wishlist.data.DataSource
import com.example.wishlist.data.model.Product

class ProductViewModel: ViewModel() {
    
    private val dataSource = DataSource()
    var productList: List<Product>? = null
    private set

    fun loadProducts() {
        productList = dataSource.getProducts()
    }

}