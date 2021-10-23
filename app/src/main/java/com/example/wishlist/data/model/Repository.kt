package com.example.wishlist.data.model

import android.content.Context
import com.example.wishlist.data.room.AppDatabase

class Repository(context: Context) {

    private val reviewDao = AppDatabase.getInstance(context).reviewDao()
    private val productDao = AppDatabase.getInstance(context).productDao()
    private val cartDao = AppDatabase.getInstance(context).cartDao()

    init {
        setProducts()
        setReviews()
    }

    /** Product **/
    fun getProduct(id: String): Product {
        return productDao.getProduct(id)
    }

    fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
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
    fun getProductReviews(id: String): List<Review> {
        return reviewDao.getReviewsByProductId(id)
    }

    fun getReview(id: String): Review {
        return reviewDao.getReviewById(id)
    }

    fun getAllReviews(): List<Review> {
        return reviewDao.getAllReviews()
    }

    fun insertReview(review: Review) {
        reviewDao.insert(review)
    }

    fun deleteReview(id: String) {
        reviewDao.deleteByReviewById(id)
    }

    /** Cart **/
    fun getCart(): Cart {
        return cartDao.getCart()
    }

    fun insertCart(cart: Cart) {
        cartDao.insertCart(cart)
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

    private fun setProducts() {
        listOf(
            Product(
                id = "101",
                name = "Playstation 5",
                price = 399.99,
                pictureUrl = "https://image.shutterstock.com/shutterstock/photos/1757486213/display_1500/stock-photo-japan-june-presentation-of-a-new-product-from-sony-wireless-white-console-playstation-1757486213.jpg",
                description = "Sony's flagship console. Beautiful sleek design. A great console for those who have money to buy from second hand sellers."
            ),
            Product(
                id = "102",
                name = "Xbox Series X",
                price = 399.99,
                pictureUrl = "https://image.shutterstock.com/z/stock-photo-boulder-co-november-microsoft-s-next-gen-gaming-consoles-are-the-xbox-series-x-and-1860770944.jpg",
                description = "What is this? Whos knows about it? A console that has been overshadowed by the understocking of PS5"
            ),
            Product(
                id = "103",
                name = "Nintendo Switch (OLED)",
                price = 349.99,
                pictureUrl = "https://cdn02.nintendo-europe.com/media/images/08_content_images/systems_5/nintendo_switch_3/nintendo_switch_oled/CI_NSwitch_main.jpg",
                description = "The console that has captured all of our hearts and our loved ones. It has brought us Animal Crossing and Super Mario Bros"

            ),
            Product(
                id = "104",
                name = "Sega Genesis",
                price = 49.99,
                pictureUrl = "https://media.gamestop.com/i/gamestop/10175135/SEGA-Genesis-Mini-Console?\$pdp2x\$",
                description = "known as the Mega Drive outside North America, is a 16-bit fourth-generation home video game console developed and sold by Sega. The Genesis was Sega's third console and the successor to the Master System"
            )
        ).forEach {
            productDao.insertProduct(it)
        }

    }
}
