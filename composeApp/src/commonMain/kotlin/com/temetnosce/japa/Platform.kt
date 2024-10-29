package com.temetnosce.japa

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform