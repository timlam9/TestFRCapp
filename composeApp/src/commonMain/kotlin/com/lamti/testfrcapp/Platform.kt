package com.lamti.testfrcapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform