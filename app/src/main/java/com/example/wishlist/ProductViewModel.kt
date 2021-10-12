package com.example.wishlist

import androidx.lifecycle.ViewModel
import com.example.wishlist.data.DataSource
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review
import com.example.wishlist.data.model.Repository

class ProductViewModel: ViewModel() {

    var reviewsList: List<Review> = Repository.getReviews()
    private set
    var productList: List<Product> = Repository.getProducts()
    private set

    fun filterReviews(productId: String): List<Review> {
        return reviewsList.filter{
            it.productId == productId
        }
    }




}