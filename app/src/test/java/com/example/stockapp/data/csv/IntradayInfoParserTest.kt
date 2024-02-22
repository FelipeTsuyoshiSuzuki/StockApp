package com.example.stockapp.data.csv

import com.example.stockapp.data.mapper.toIntradayInfo
import com.example.stockapp.data.remote.dto.IntradayInfoDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class IntradayInfoParserTest {

    val csv = """
        timestamp,open,high,low,close,volume
        2024-02-21 19:55:00,181.2500,181.6000,181.2500,181.3500,18
        2024-02-21 19:50:00,181.0000,181.6000,180.8500,181.3600,735
        2024-02-21 19:45:00,181.0000,181.2400,181.0000,181.2400,34
    """.trimIndent().byteInputStream()

    private var intradayInfoParser = IntradayInfoParser()
    @Test
    fun `CompanyListParser parsing csv file to CompanyListing`() = runTest {
        val list = intradayInfoParser.parse(csv)
        val expect = listOf(
            IntradayInfoDto("2024-02-21 19:55:00", 181.3500).toIntradayInfo(),
            IntradayInfoDto("2024-02-21 19:50:00", 181.3600).toIntradayInfo(),
            IntradayInfoDto("2024-02-21 19:45:00", 181.2400).toIntradayInfo()
        )
        assertThat(list).isEqualTo(expect)
    }
}