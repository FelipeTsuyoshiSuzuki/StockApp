package com.example.stockapp.utils

import java.io.InputStreamReader

class MockResponseReader(path: String) {
    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content = reader.readText().removeSurrounding("\"")
        reader.close()
    }
}
