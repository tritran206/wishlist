package com.example.week3.data

import com.example.week3.data.model.Product

class DataSource {


    fun getProducts(): List<Product> {
        return listOf(
            Product(
                name = "Playstation 5",
                price = 399.99,
                pictureUrl = "https://image.shutterstock.com/shutterstock/photos/1757486213/display_1500/stock-photo-japan-june-presentation-of-a-new-product-from-sony-wireless-white-console-playstation-1757486213.jpg"
            ),
            Product(
                name = "Xbox Series X",
                price = 399.99,
                pictureUrl = "https://image.shutterstock.com/z/stock-photo-boulder-co-november-microsoft-s-next-gen-gaming-consoles-are-the-xbox-series-x-and-1860770944.jpg"
            ),
            Product(
                name = "Nintendo Switch (OLED)",
                price = 349.99,
                pictureUrl = "https://cdn02.nintendo-europe.com/media/images/08_content_images/systems_5/nintendo_switch_3/nintendo_switch_oled/CI_NSwitch_main.jpg"
            ),
            Product(
                name = "Sega Genesis",
                price = 49.99,
                pictureUrl = "https://media.gamestop.com/i/gamestop/10175135/SEGA-Genesis-Mini-Console?\$pdp2x\$"
            )
        )

    }
}