package com.example.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.wishlist.data.DataSource
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review
import com.example.wishlist.data.model.Repository

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository = Repository(context)

    var reviewsList: List<Review> = repository.getAllReviews()
    private set
    var productList: List<Product> = repository.getAllProducts()
    private set

    fun getProduct(id: String): Product {
        return repository.getProduct(id)
    }

    fun addReview(review: Review) {
        repository.insertReview(review)
    }

    fun filterReviews(productId: String): List<Review> {
        return reviewsList.filter{
            it.productId == productId
        }
    }

}