package com.example.currencyconverter.repo

import com.example.currencyconverter.data.Client
import com.example.currencyconverter.model.ExchangeRateResponse

class ExchangeRatesRepository {
    private val apiService = Client.RetrofitInstance.api

    suspend fun getExchangeRates( from :String , to:String,amount: Double ): ExchangeRateResponse {
        return apiService.getExchangeRate(from, to , amount)
    }
}