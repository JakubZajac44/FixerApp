package com.interview.qpony.repository

import com.interview.qpony.Constants.Companion.API_CURRENCY
import com.interview.qpony.Constants.Companion.API_KEY
import com.interview.qpony.model.ExchangeRate
import com.interview.qpony.service.FixerApi
import io.reactivex.Observable
import retrofit2.Response

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FixerRepository @Inject constructor (var fixerApi: FixerApi){

    fun getCurrencyByDate(date: String) : Observable<Response<ExchangeRate>> {
        return fixerApi.getCurrency(date, API_KEY, API_CURRENCY)
    }
}