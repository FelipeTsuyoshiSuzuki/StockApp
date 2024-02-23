package com.example.stockapp.utils

import com.example.stockapp.data.remote.StockApi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerDispatcher {

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/query?function=LISTING_STATUS&apikey=${StockApi.API_KEY}" -> MockResponse()
                    .setResponseCode(200)
                    .setBody(MockResponseReader("listing_status_response_success.csv").content)
                "/query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv&symbol=IBM&apikey=${StockApi.API_KEY}" ->
                    MockResponse().setResponseCode(200)
                        .setBody(MockResponseReader("intraday_info.response_success.csv").content)
                "/query?function=OVERVIEW&symbol=IBM&apikey=${StockApi.API_KEY}" ->
                    MockResponse().setResponseCode(200)
                        .setBody(MockResponseReader("company_info_response_success.json").content)
                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
                .setBody("Error Dispathcer")
        }
    }
}