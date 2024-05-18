package io.reyurnible.order

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform