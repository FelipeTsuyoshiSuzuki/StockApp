package com.example.stockapp.utils

import com.google.common.truth.Truth
import org.junit.Test

class MockResponseReaderTest {

    @Test
    fun read_simple_txt_file() {
        val reader = MockResponseReader("test.txt")
        Truth.assertThat(reader.content).isEqualTo("success")
    }

    @Test
    fun read_simple_json_file() {
        val reader = MockResponseReader("test.json")
        Truth.assertThat(reader.content).isEqualTo("success")
    }
}