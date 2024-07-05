package com.example.currencyconverter.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    object RetrofitInstance {
        private const val BASE_URL = "https://v6.exchangerate-api.com/v6/294eca2e4a2293cd054edf66/"

        val api: ExchangeRateApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExchangeRateApi::class.java)
        }
    }
}
