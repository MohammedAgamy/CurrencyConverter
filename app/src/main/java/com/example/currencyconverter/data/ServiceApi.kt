package com.example.currencyconverter.data

import com.example.currencyconverter.model.ModelConvert
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("fetch-multi?")
    fun getLatestRates(
        @Query("access_key") accessKey: String,
        @Query("base") from: String,
        @Query("results") to: String
    ): Call<ModelConvert>
}