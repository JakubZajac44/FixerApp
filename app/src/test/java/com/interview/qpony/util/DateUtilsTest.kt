package com.interview.qpony.util

import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DateUtilsTest {

    private val dataUtils = DateUtils()

    @Test
    fun `is string data valid return true`() {
        val result = dataUtils.isStringValidDate("2020-11-11")
        assertTrue(result)
    }

    @Test
    fun `is string data valid return false`() {
        val result = dataUtils.isStringValidDate("wrong format")
        assertFalse(result)
    }

    @Test
    fun `decrease one day`() {
        val result = dataUtils.decreaseOneDay(dataUtils.convertStringDateToFormat("2020-11-11"))
        val expected = dataUtils.convertStringDateToFormat("2020-11-10")
        Assert.assertEquals(
            "Convert Currency failed", expected,
            result
        )
    }


}