package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //USE BINDING INSTEAD OF findViewById this makes work upfront easier
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener{
            calculateTip()
        }
    }
    private fun calculateTip()
    {
        val str = binding.costOfServiceEditText.text.toString()
        val cost = str.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_fifteen_percent -> 0.15
            R.id.option_eighteen_percent -> 0.18
            else -> 0.00
        }
        var tip = tipPercentage * cost
        val roundup = binding.roundUpSwitch.isChecked
        if(roundup){
            tip = kotlin.math.ceil(tip)
        }
        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatTip)
    }
}