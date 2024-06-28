package com.example.currencyconverter.data

import com.example.currencyconverter.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("294eca2e4a2293cd054edf66/pair/{from}/{to}/{amount}")
    fun getExchangeRate(
        @Path("from") fromCurrency: String,
        @Path("to") toCurrency: String,
        @Path("amount") amount: Double
    ): Call<ExchangeRateResponse>
}