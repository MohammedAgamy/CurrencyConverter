package com.example.currencyconverter.data

import com.example.currencyconverter.model.ExchangeRateAllResponse
import com.example.currencyconverter.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("pair/{from}/{to}/{amount}")
    suspend fun getExchangeRate(
        @Path("from") fromCurrency: String,
        @Path("to") toCurrency: String,
        @Path("amount") amount: Double
    ):ExchangeRateResponse



       @GET("latest/{base}")
       fun getAllCurrency(@Path("base") base:String):Call<ExchangeRateAllResponse>
}