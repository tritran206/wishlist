package com.example.wishlist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wishlist.data.model.CartItem
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.Review
import com.example.wishlist.data.model.Repository
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val repository = Repository(context)

    private val _reviewList = repository.getAllReviews()
    val reviewList
        get() = _reviewList
    val productList = MutableLiveData<List<Product>>()
    var currentProductList = emptyList<Product>()
        private set
    // Add backing property vs private set
    var cart: LiveData<List<CartItem>> = repository.getCart()
        private set

    init{
        getAllProducts2()
    }

    fun getProduct(id: String): Product {
        return currentProductList.first{
            it.productId == id
        }
    }

    fun getAllProducts2(){
        val mRunnable = Runnable {
            productList.postValue(repository.getAllProducts2())
            Log.i("ProductResponse", "$productList")
        }
        val mExecutor: Executor = Executors.newSingleThreadExecutor()
        mExecutor.execute(mRunnable)
    }

    fun getReview(id: String): Review {
        return repository.getReview(id)
    }

    fun addReview(review: Review) {
        repository.insertReview(review)
    }

    fun filterReviews(productId: String, reviews: List<Review>): List<Review> {
        return reviews.filter {
            it.productId == productId
        }
    }

    fun getProductsFromIds(items: List<CartItem>): List<Product> {
        val products = mutableListOf<Product>()
        items.forEach { cartItem ->
            currentProductList.firstOrNull { product ->
                product.productId == cartItem.productId
            }?.let {
                products.add(it)
            }
        }
        return products
    }

    fun addProductToCart(itemId: String) {
        val cartItem = CartItem(0, productId = itemId)
        repository.insertCartItem(cartItem)
    }

    fun updateCurrentProductList(productList: List<Product>) {
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
    }
}

