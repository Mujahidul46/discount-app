package com.example.salecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.util.Log
import java.text.DecimalFormat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var etInitialPrice: EditText
    private lateinit var etPercentageDiscount: EditText
    private lateinit var etTaxPercentage: EditText
    private lateinit var tvAmountSavedAmount: TextView
    private lateinit var tvFinalPriceAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etInitialPrice = findViewById(R.id.etInitialPrice)
        etPercentageDiscount = findViewById(R.id.etPercentageDiscount)
        etTaxPercentage = findViewById(R.id.etTaxPercentage)
        tvAmountSavedAmount = findViewById(R.id.tvAmountSavedAmount)
        tvFinalPriceAmount = findViewById(R.id.tvFinalPriceAmount)

        etInitialPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeAmountSavedAndFinalPrice()
            }
        })

        // Add similar TextWatchers for etPercentageDiscount and etTaxPercentage to trigger computation on their changes.
        etPercentageDiscount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeAmountSavedAndFinalPrice()
            }
        })

        etTaxPercentage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeAmountSavedAndFinalPrice()
            }
        })
    }

    private fun computeAmountSavedAndFinalPrice() {
        // 1. Get the values for initial price, discount, and tax
        val initialPriceStr = etInitialPrice.text.toString()
        val discountPercentageStr = etPercentageDiscount.text.toString()
        val taxPercentageStr = etTaxPercentage.text.toString()

        // 2. Convert empty input fields to 0, or parse them to Double if not empty
        val initialPrice = if (initialPriceStr.isNotEmpty()) initialPriceStr.toDouble() else 0.0
        val discountPercentage = if (discountPercentageStr.isNotEmpty()) discountPercentageStr.toDouble() else 0.0
        val taxPercentage = if (taxPercentageStr.isNotEmpty()) taxPercentageStr.toDouble() else 0.0

        // 3. Compute the amount saved and final price
        val amountSaved = (discountPercentage / 100) * initialPrice
        val finalPrice = (initialPrice - amountSaved) + ((taxPercentage / 100) * initialPrice)

        // 4. Round the values to two decimal places
        val roundedAmountSaved = String.format("%.2f", amountSaved)
        val roundedFinalPrice = String.format("%.2f", finalPrice)

        // 5. Update the UI
        tvAmountSavedAmount.text = roundedAmountSaved
        tvFinalPriceAmount.text = roundedFinalPrice
    }

}
