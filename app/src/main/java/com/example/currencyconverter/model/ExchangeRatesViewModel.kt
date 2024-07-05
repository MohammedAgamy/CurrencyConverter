package com.example.currencyconverter.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.repo.ExchangeRatesRepository
import kotlinx.coroutines.launch

class ExchangeRatesViewModel(private val repository: ExchangeRatesRepository) : ViewModel() {

    val exchangeRates = MutableLiveData<ExchangeRateResponse>()
    val errorMessage = MutableLiveData<String>()


    fun fetchExchangeRates(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            try {
                val result = repository.getExchangeRates(from, to, amount)
                exchangeRates.postValue(result)
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
            }
        }
    }

    class ExchangeRatesViewModelFactory(private val repository: ExchangeRatesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExchangeRatesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ExchangeRatesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}