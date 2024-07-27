package com.example.calculator

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var currentInput: String = ""
    var oprator: String = ""
    var firstOperand: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    fun onDigitClick(view: View) {
        currentInput += (view as Button).text
        binding.textView.text = currentInput
    }

    fun onOperatorClick(view: View) {
        oprator = (view as Button).text.toString()
        binding.textView.text = oprator
        firstOperand = currentInput.toDoubleOrNull() ?: 0.0
        binding.textView3.text = "$firstOperand $oprator"
        currentInput = ""
    }

    fun onEqualClick(view: View) {
        val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
        binding.textView3.text = ""
        binding.textView3.text = "$firstOperand $oprator $secondOperand ="
        val result = when (oprator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "x" -> firstOperand * secondOperand
            "÷" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN

            else -> 0.0
        }
        currentInput = result.toString()
        binding.textView.text = currentInput
    }

    fun onBackspaceClick(view: View) {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length - 1)
            binding.textView.text = currentInput
        }
    }

    fun onDecimalClick(view: View) {
        if (!currentInput.contains(".")) {
            currentInput += "."
            binding.textView.text = currentInput
        }
    }

    fun onClearClick(view: View) {
        currentInput = ""
        oprator = ""
        firstOperand = 0.0
        binding.textView.text = currentInput
        binding.textView3.text = currentInput
    }
}