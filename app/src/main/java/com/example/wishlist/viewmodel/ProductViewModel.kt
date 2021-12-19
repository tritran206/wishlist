package com.example.wishlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.wishlist.data.model.CartItem
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review
import com.example.wishlist.data.model.Repository

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository = Repository(context)

    private val _reviewList = repository.getAllReviews()
    val reviewList
        get() = _reviewList

    var productList: LiveData<List<Product>> = repository.getAllProducts()
    private set

    var currentProductList = emptyList<Product>()
    private set

    // Add backing property vs private set
    var cart: LiveData<List<CartItem>> = repository.getCart()
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

    fun filterReviews(productId: String, reviews: List<Review>): List<Review> {
        return reviews.filter{
            it.productId == productId
        }
    }

    fun getProductsFromIds(items: List<CartItem>): List<Product> {
        val products = mutableListOf<Product>()
        items.forEach { cartItem ->
            currentProductList.firstOrNull { product ->
                product.id == cartItem.productId
            }?.let {
                products.add(it)
            }

        }
        return products
    }

   //TODO update when livedata is implemented
    fun addProductToCart(itemId: String) {
        val cartItem = CartItem(0, productId = itemId)
        repository.insertCartItem(cartItem)
    }

    fun updateCurrentProductList(productList: List<Product>){
        currentProductList = productList
    }

    companion object {
        private var instance: ProductViewModel? = null
        fun getInstance(application: Application): ProductViewModel {
            if (instance == null) {
                instance = ProductViewModel(application)
            }
            return instance!!
        }
}}

