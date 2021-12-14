package com.interview.qpony.util

import com.interview.qpony.Constants.Companion.AUD_CURRENCY
import com.interview.qpony.Constants.Companion.CAD_CURRENCY
import com.interview.qpony.Constants.Companion.CHF_CURRENCY
import com.interview.qpony.Constants.Companion.CNY_CURRENCY
import com.interview.qpony.Constants.Companion.GBP_CURRENCY
import com.interview.qpony.Constants.Companion.JPY_CURRENCY
import com.interview.qpony.Constants.Companion.PLN_CURRENCY
import com.interview.qpony.Constants.Companion.USD_CURRENCY
import com.interview.qpony.model.CurrencyItemWrapper
import com.interview.qpony.model.CurrencyItemWrapperType
import com.interview.qpony.model.ExchangeRate

fun convertCurrency(exchangeRate: ExchangeRate?) : List<CurrencyItemWrapper>{
    val currencyItemWrapperList = mutableListOf<CurrencyItemWrapper>()
    exchangeRate?.let{
        currencyItemWrapperList.add(CurrencyItemWrapper(date = it.date, type = CurrencyItemWrapperType.DATE))
        val currency = it.rates
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.USD, currencyName =USD_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.AUD, currencyName =AUD_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.PLN, currencyName =PLN_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.CAD, currencyName =CAD_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.CHF, currencyName =CHF_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.CNY, currencyName =CNY_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.GBP, currencyName =GBP_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
        currencyItemWrapperList.add(CurrencyItemWrapper(value = currency.JPY, currencyName =JPY_CURRENCY,  date = it.date, type = CurrencyItemWrapperType.CURRENCY))
    }

    return currencyItemWrapperList
}