package com.example.stockapp.data.csv

import com.example.stockapp.domain.model.CompanyListing
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CompanyListParserTest {

    val csv = """
        symbol,name,exchange,assetType,ipoDate,delistingDate,status
        A,Agilent Technologies Inc,NYSE,Stock,1999-11-18,null,Active
        AA,Alcoa Corp,NYSE,Stock,2016-10-18,null,Active
        AAA,AXS First Priority CLO Bond ETF,NYSE ARCA,ETF,2020-09-09,null,Active
    """.trimIndent().byteInputStream()

    private var companyListParser = CompanyListParser()
    @Test
    fun `CompanyListParser parsing csv file to CompanyListing`() = runTest {
        val list = companyListParser.parse(csv)
        val expect = listOf(
            CompanyListing("Agilent Technologies Inc", "A", "NYSE"),
            CompanyListing("Alcoa Corp", "AA", "NYSE"),
            CompanyListing("AXS First Priority CLO Bond ETF", "AAA", "NYSE ARCA")
        )
        assertThat(list).isEqualTo(expect)
    }
}