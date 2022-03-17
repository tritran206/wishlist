package com.example.wishlist.data.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wishlist.data.room.AppDatabase
import com.example.wishlist.services.ProductService
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Repository(context: Context) {

    private val reviewDao = AppDatabase.getInstance(context).reviewDao()
    private val productDao = AppDatabase.getInstance(context).productDao()
    private val cartDao = AppDatabase.getInstance(context).cartDao()
    private val productService = ProductService.instance


    init {
        setReviews()
    }

    /** Product **/
    fun getProduct(id: String): Product {
        return productDao.getProduct(id)
    }

    fun getAllProducts2(): List<Product>? {
        return productService.getProducts(1, 7).execute().body()?.products
    }

//
//    fun insertProduct(product: Product) {
//        productDb.insertProduct(product)
//    }
//
//    fun deleteProduct(id: String) {
//        productDb.deleteProduct(id)
//    }

    /** Reviews **/
    fun getProductReviews(id: String): LiveData<List<Review>> {
        return reviewDao.getReviewsByProductId(id)
    }

    fun getReview(id: String): Review {
        return reviewDao.getReviewById(id)
    }

    fun getAllReviews(): LiveData<List<Review>> {
        return reviewDao.getAllReviews()
    }

    fun insertReview(review: Review) {
        reviewDao.insert(review)
    }

    fun deleteReview(id: String) {
        reviewDao.deleteByReviewById(id)
    }

    /** Cart **/
    fun getCart(): LiveData<List<CartItem>> {
        return cartDao.getCart()
    }

    fun insertCartItem(cartItem: CartItem) {
        cartDao.insertItem(cartItem)
    }

    fun removeItemById(id: String){
        cartDao.removeItemById(id)
    }

    fun clearCart() {
        cartDao.clearCart()
    }

    /** Setup */
    private fun setReviews() {
        listOf(
            Review(
                productId = "101",
                id = "1",
                rating = 1.0,
                user = "Bob",
                text = "Ordering this from walmart was a very hard task, from the beginning to the end, when i can finally get it, it comes broken, the box has a damage in one of the corners, and when i open it, also the console, talk to customer service and the only solution they gave me was a refund. Very disappointing."
            ),
            Review(
                productId = "102",
                id = "2",
                rating = 5.0,
                user = "Billy",
                text = "I have waited two months to give an honest review of this because I have had an Xbox ever since the PS1. I love my Xbox and the performance, the PS5 had some issues for me at first in multiplayer games. Once I ran a WiFi extender and used Ethernet off of it, all the lagging has went away. The controller was hard to get used to at first but now it feels very comfortable to me and is definitely next gen. I love the PS5 for its looks, the way it performs and the remote controller. Everything about this system is great. Once I got used to the UI and the remote it is amazing. I have both the Series X and PS5, either of them are amazing, the Xbox wireless headset and Pulse 3D are amazing. You can't go wrong with either, but the controller on the PS5 is truly next gen. The Xbox controller is just comfortable in your hand. Overall, this console performs amazingly, they just need to release the storage capacity already!"
            )
        ).forEach {
            reviewDao.insert(it)
        }
    }
}
