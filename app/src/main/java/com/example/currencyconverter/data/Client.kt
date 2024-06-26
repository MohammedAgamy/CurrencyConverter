package com.example.currencyconverter.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object {
        val BaseUrl: String = "http://data.fixer.io/api/"
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}