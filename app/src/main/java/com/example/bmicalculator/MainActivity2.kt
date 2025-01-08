package com.example.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setup()
    }

    private lateinit var textViews: List<TextView>
    private lateinit var textViewBMI: TextView
    private lateinit var button: Button

    private fun setup() {
        textViews = listOf(
            findViewById(R.id.textViewName),
            findViewById(R.id.textViewAddress),
            findViewById(R.id.textViewBirthday),
            findViewById(R.id.textViewContactNo),
            findViewById(R.id.textViewCourseAndYear),
            findViewById(R.id.textViewHeight),
            findViewById(R.id.textViewWeight)
        )

        val data = listOf(
            intent.getStringExtra("name"),
            intent.getStringExtra("address"),
            intent.getStringExtra("birthday"),
            intent.getStringExtra("contactNo"),
            intent.getStringExtra("courseAndYear"),
            intent.getStringExtra("height"),
            intent.getStringExtra("weight"),
            intent.getStringExtra("heightUnit"),
            intent.getStringExtra("weightUnit")
        )

        for (i in textViews.indices) {
            textViews[i].text = data[i] ?: "No data"
        }

        textViewBMI = findViewById(R.id.textViewBMI)

        textViewBMI.text = BMICalculator.calculateBMI(data[5]!!.toDouble(), data[6]!!.toDouble(), data[7].toString(), data[8].toString())

        button = findViewById(R.id.button)

        button.setOnClickListener{
            finish()
        }
    }
}