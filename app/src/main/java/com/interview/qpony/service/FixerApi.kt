package com.interview.qpony.service

import com.interview.qpony.model.ExchangeRate
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FixerApi {

    @GET("{date}")
    fun getCurrency(@Path("date")  date : String,  @Query("access_key") page: String, @Query("symbols") symbols:String): Observable<Response<ExchangeRate>>
}