package com.example.mycalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        val add:Button=findViewById(R.id.btnPlus)
        val sub:Button=findViewById(R.id.btnMinus)
        val mul:Button=findViewById(R.id.btnMultiply)
        val div:Button=findViewById(R.id.btnDivide)

        val equal:Button=findViewById(R.id.btnEquals)
        val clear:Button=findViewById(R.id.btnClear)
        val backspace:Button=findViewById(R.id.btnBackspace)

        val dot:Button=findViewById(R.id.btnDot)
        val percent:Button=findViewById(R.id.btnPercent)


        btn0.setOnClickListener {appendNumber("0")}
        btn1.setOnClickListener {appendNumber("")}
        btn2.setOnClickListener {appendNumber("2")}
        btn3.setOnClickListener {appendNumber("3")}
        btn4.setOnClickListener {appendNumber("4")}
        btn5.setOnClickListener {appendNumber("5")}
        btn6.setOnClickListener {appendNumber("6")}
        btn7.setOnClickListener {appendNumber("7")}
        btn8.setOnClickListener {appendNumber("8")}
        btn9.setOnClickListener {appendNumber("9")}
        dot.setOnClickListener {appendNumber(".")}


        percent.setOnClickListener {setOperation("%")}
        add.setOnClickListener { setOperation("+") }
        sub.setOnClickListener { setOperation("-") }

        mul.setOnClickListener { setOperation("×") }
        div.setOnClickListener { setOperation("÷") }

        equal.setOnClickListener { calculateResult() }
        clear.setOnClickListener { clearCalculator() }

        backspace.setOnClickListener { deleteNum() }
    }

    private fun deleteNum() {
        if (resultTextView.text.isNotEmpty() && resultTextView.text != "0.0" && resultTextView.text != "Error") {
            resultTextView.text=resultTextView.text.dropLast(1)
        }
        else{
            Toast.makeText(this,"Invalid Operation",Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearCalculator() {
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
        resultTextView.text="0.0"
        previousCalculationTextView.text=""
    }

    private fun calculateResult() {
try{
        val secondNumber = resultTextView.text.toString().toDouble()
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "×" -> firstNumber * secondNumber
            "÷" -> firstNumber / secondNumber
            "%" -> firstNumber % secondNumber
            else -> secondNumber
        }
            previousCalculationTextView.text="$firstNumber $operation $secondNumber"
resultTextView.text=result.toString()
    isNewOperation=true

    }
catch (e:Exception){
    resultTextView.text="Error"


}}

    private fun setOperation(operator: String) {
        firstNumber = resultTextView.text.toString().toDouble()
        operation = operator
        isNewOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
        resultTextView.text="0.0"


    }

    private fun appendNumber(number:String){
        if(isNewOperation){
            resultTextView.text=number
            isNewOperation=false
        }
        else{
            resultTextView.text="${resultTextView.text}$number"
        }

    }
}