package com.interview.qpony.util

import com.interview.qpony.model.Currency
import com.interview.qpony.model.ExchangeRate
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyConverterKtTest {
    @Test
    fun `currency converter null object return list size 0`() {
        val actual = convertCurrency(null)
        val expected = 0

        assertEquals(
            "Convert Currency failed", expected,
            actual.size
        )
    }

    @Test
    fun `currency converter 1 object return list size 9`() {
        val actual = convertCurrency(ExchangeRate(true,"2020-06-6", Currency(0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f)))
        val expected = 9

        assertEquals(
            "Convert Currency failed", expected,
            actual.size
        )
    }
}