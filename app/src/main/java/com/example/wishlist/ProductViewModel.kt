package com.example.wishlist

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

    var reviewsList: List<Review> = repository.getAllReviews()
    private set
    var productList: List<Product> = repository.getAllProducts()
    private set
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

    fun filterReviews(productId: String): List<Review> {
        return reviewsList.filter{
            it.productId == productId
        }
    }

    fun getProductsFromIds(items: List<CartItem>): List<Product> {
        val products = mutableListOf<Product>()
        items.forEach { cartItem ->
            products.add(
                productList.first { product ->
                    product.id == cartItem.productId
                }
            )
        }
        return products
    }

   //TODO update when livedata is implemented
    fun addProductToCart(itemId: String) {
        val cartItem = CartItem(0, productId = itemId)
        repository.insertCartItem(cartItem)
    }
}