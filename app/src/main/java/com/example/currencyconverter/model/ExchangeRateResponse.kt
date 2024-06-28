package com.example.currencyconverter.model

data class ExchangeRateResponse(
    val base_code: String,
    val target_code: String,
    val conversion_result: Double,


)