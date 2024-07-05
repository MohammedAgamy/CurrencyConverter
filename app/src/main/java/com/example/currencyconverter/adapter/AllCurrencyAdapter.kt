package com.example.currencyconverter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R

class AllCurrencyAdapter(private val exchangeRates: Map<String, Double>) :
    RecyclerView.Adapter<AllCurrencyAdapter.ExchangeRateViewHolder>() {


    class ExchangeRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyCode: TextView = itemView.findViewById(R.id.currencyCode)
        val exchangeRate: TextView = itemView.findViewById(R.id.exchangeRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_allcurrency_item, parent, false)
        return ExchangeRateViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return exchangeRates.size

    }

    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        val currency = exchangeRates.keys.elementAt(position)
        val rate = exchangeRates[currency]
        holder.currencyCode.text = currency
        holder.exchangeRate.text = rate.toString()
    }

}

