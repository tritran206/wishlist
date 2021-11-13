package com.example.wishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wishlist.data.model.CartItem
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
    var cart = repository.getCart()
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
        val shoppingList = cart
        var products = mutableListOf<Product>()
        shoppingList.forEach {
            products.add(getProduct(it.itemId))
        }
        return products
    }


   //TODO update when livedata is implemented
    fun addProductToCart(itemId: String) {
        var cartItem = CartItem(0, itemId = itemId)
        repository.insertCart(cartItem)
        val updatedCart = cart + cartItem
        cart = updatedCart
    }
}