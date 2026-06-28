package com.example.products_ecommers

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform