package com.ssverma.theearth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform