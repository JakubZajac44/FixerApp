package com.interview.qpony.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Singleton
class DateUtils {


    fun convertDateFormatToString(date: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(date)
    }

    fun isStringValidDate(dateString: String): Boolean{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            sdf.parse(dateString)
        } catch (e: ParseException) {
            return false
        }
        return true
    }

    fun convertStringDateToFormat(date: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.parse(date)
    }

    fun decreaseOneDay(date: Date): Date {
        val c = Calendar.getInstance()
        c.time = date
        c.add(Calendar.DATE, -1)
        return c.time
    }

}


