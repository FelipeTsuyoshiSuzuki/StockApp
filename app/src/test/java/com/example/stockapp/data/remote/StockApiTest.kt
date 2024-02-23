package com.example.stockapp.data.remote

import com.example.stockapp.utils.MockWebServerDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StockApiTest {
    private lateinit var api: StockApi
    private lateinit var server : MockWebServer
    private val client  = OkHttpClient.Builder().build()
    @Before
    fun setup() {
        server = MockWebServer()
        server.start()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun `getListing proper call`() = runTest {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()
        api.getListing()
    }

    @Test
    fun `getIntradayInfo proper call`() = runTest {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()
        api.getIntradayInfo("IBM")
    }

    @Test
    fun `getCompanyInfo proper call`() = runTest {
        server.dispatcher = MockWebServerDispatcher().RequestDispatcher()
        api.getCompanyInfo("IBM")
    }

}