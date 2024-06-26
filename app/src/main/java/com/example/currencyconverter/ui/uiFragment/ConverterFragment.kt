package com.example.currencyconverter.ui.uiFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.currencyconverter.R
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class ConverterFragment : Fragment() {


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
        val countrySpinner: MaterialAutoCompleteTextView = view.findViewById(R.id.countrySpinner)

        // Get the string array resource
        val countries = resources.getStringArray(R.array.countries_array)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, countries)

        // Set the adapter to the MaterialAutoCompleteTextView
        countrySpinner.setAdapter(adapter)

    }
}