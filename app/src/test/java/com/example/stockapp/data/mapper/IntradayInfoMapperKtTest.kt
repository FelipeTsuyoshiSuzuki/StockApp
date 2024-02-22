package com.example.stockapp.data.mapper

import com.example.stockapp.data.remote.dto.IntradayInfoDto
import com.example.stockapp.domain.model.IntradayInfo
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDateTime

class IntradayInfoMapperKtTest {

    @Test
    fun `IntradayInfoDto convert to IntradayInfo`() {
        val dto = IntradayInfoDto("2024-02-21 19:55:00", 181.3500)
        val expect = IntradayInfo(
            LocalDateTime.of(2024, 2, 21, 19, 55, 0),
            181.3500
        )

        val result = dto.toIntradayInfo()

        assertThat(result).isEqualTo(expect)
    }
}