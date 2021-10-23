package com.example.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wishlist.data.model.Cart
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
    var cart = repository.getCart() ?: Cart()
    private set

    fun getProduct(id: String): Product {
        return repository.getProduct(id)
    }

    fun getReview(id: String): Review {
        return repository.getReview(id)
    }

    fun addReview(review: Review) {
        repository.insertReview(review)
    }

    fun filterReviews(productId: String): List<Review> {
        return reviewsList.filter{
            it.productId == productId
        }
    }

    fun getProductsForCart(): List<Product> {
        val shoppingList = cart.productIds
        var products = mutableListOf<Product>()
        shoppingList.forEach {
            products.add(getProduct(it))
        }
        return products
    }

//    saves cart before ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        repository.insertCart(cart)
    }

    fun addProductToCart(id: String) {
        cart.addToCart(id)
    }
}