package com.example.currencyconverter.model

data class ModelConvert(
    val base: String,
    val ms: Int,
    val results: Results,
    val updated: String
)