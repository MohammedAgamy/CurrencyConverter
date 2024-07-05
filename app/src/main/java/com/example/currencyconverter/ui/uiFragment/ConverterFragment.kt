package com.example.currencyconverter.ui.uiFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.data.Client
import com.example.currencyconverter.model.ExchangeRateResponse
import com.example.currencyconverter.model.ExchangeRatesViewModel
import com.example.currencyconverter.repo.ExchangeRatesRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback

class ConverterFragment : Fragment() {
    private lateinit var countrySpinnerfrom: MaterialAutoCompleteTextView
    private lateinit var countrySpinnerTo: MaterialAutoCompleteTextView
    private lateinit var amountView: TextView
    private lateinit var concertBtn: MaterialButton
    private lateinit var allCurrencyBtn: MaterialButton
    private lateinit var amountInput: TextInputEditText

    private var fromCurrency: String = "null"
    private var toCurrency: String = "null"
    private var amount = 2.0


    private val repository = ExchangeRatesRepository()
    private val viewModel: ExchangeRatesViewModel by viewModels {
        ExchangeRatesViewModel.ExchangeRatesViewModelFactory(
            repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Find the MaterialAutoCompleteTextView in the layout
        countrySpinnerfrom = view.findViewById(R.id.countrySpinnerfrom)
        countrySpinnerTo = view.findViewById(R.id.countrySpinnerto)

        amountView = view.findViewById(R.id.finalAmount)
        concertBtn = view.findViewById(R.id.materialButton)
        allCurrencyBtn = view.findViewById(R.id.showAllCurancy)
        amountInput = view.findViewById(R.id.amount)



        getFrom()
        getTo()

        concertBtn.setOnClickListener {
            // callApi()

            // Fetch exchange rates for a specific currency
            viewModel.fetchExchangeRates(fromCurrency, toCurrency, amount)

            // Observe the exchange rates data
            viewModel.exchangeRates.observe(requireActivity(), Observer { rates ->
                // Update the UI with the fetched exchange rates
                val amount = rates.conversion_result
                amountView.text = amount.toString()

            })

            // Observe the error message
            viewModel.errorMessage.observe(requireActivity(), Observer { message ->
                // Show error message
                Log.d(
                    "MainActivity",
                    "Conversion Result: $message"
                )
            })


        }
        allCurrencyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_converterFragment_to_allCurrencyFragment)
        }


    }

    /* private fun callApi() {
         val amountText = amountInput.text.toString()
         amount = amountText.toDouble()
         Client.RetrofitInstance.api.getExchangeRate(fromCurrency, toCurrency, amount)
             .enqueue(object : Callback<ExchangeRateResponse> {
                 override fun onResponse(
                     call: Call<ExchangeRateResponse>,
                     response: retrofit2.Response<ExchangeRateResponse>
                 ) {
                     if (response.isSuccessful) {
                         val exchangeRateResponse = response.body()
                         if (exchangeRateResponse != null) {
                             Log.d(
                                 "MainActivity",
                                 "Conversion Result: ${exchangeRateResponse.conversion_result}"
                             )

                             val amount = exchangeRateResponse.conversion_result
                             amountView.text = amount.toString()
                         } else {
                             Log.e(
                                 "MainActivity",
                                 "Request failed with error: ${response.errorBody()?.string()}"
                             )
                         }
                     }
                 }

                 override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                     Log.e("MainActivity", "Network request failed: ${t.message}")
                 }
             })
     }*/

    fun getFrom() {
        // Get the string array resource
        val countries = resources.getStringArray(R.array.countries_array)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, countries)

        // Set the adapter to the MaterialAutoCompleteTextView
        countrySpinnerfrom.setAdapter(adapter)

        countrySpinnerfrom.setOnItemClickListener { parent, view, position, id ->

            fromCurrency = adapter.getItem(position).toString()
            countrySpinnerfrom.keyListener = null

            Toast.makeText(requireActivity(), "Selected: $fromCurrency", Toast.LENGTH_SHORT).show()
        }


    }

    fun getTo() {
        // Get the string array resource
        val countries = resources.getStringArray(R.array.countries_array)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, countries)

        // Set the adapter to the MaterialAutoCompleteTextView
        countrySpinnerTo.setAdapter(adapter)

        countrySpinnerTo.setOnItemClickListener { parent, view, position, id ->

            toCurrency = adapter.getItem(position).toString()
            countrySpinnerTo.keyListener = null

            Toast.makeText(requireActivity(), "Selected: $toCurrency", Toast.LENGTH_SHORT).show()
        }


    }
}