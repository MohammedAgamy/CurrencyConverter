package com.example.currencyconverter.ui.uiFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.adapter.AllCurrencyAdapter
import com.example.currencyconverter.data.Client
import com.example.currencyconverter.model.ExchangeRateAllResponse
import com.example.currencyconverter.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AllCurrencyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllCurrencyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView_allCurrency)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        getAllCurrency()

    }

    private fun getAllCurrency() {
        Client.RetrofitInstance.api.getAllCurrency("USD")
            .enqueue(object : Callback<ExchangeRateAllResponse> {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(
                    call: Call<ExchangeRateAllResponse>,
                    response: Response<ExchangeRateAllResponse>
                ) {
                    if (response.isSuccessful) {
                        val exchangeRateResponse = response.body()
                        exchangeRateResponse?.let {
                                adapter = AllCurrencyAdapter(it.conversion_rates)
                                recyclerView.adapter = adapter

                        }
                            Log.d("ExchangeRates", "Rates: ${exchangeRateResponse!!.conversion_rates}")

                    } else {
                        Log.e("Error", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<ExchangeRateAllResponse>, t: Throwable) {
                    Log.e("Error", "Network call failed: ${t.message}")
                }

            })
    }

}