    package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setup()
    }

    private lateinit var editTexts: List<EditText>
    private lateinit var button: Button
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner

    private fun setup() {
        editTexts = listOf(
            findViewById(R.id.editTextName),
            findViewById(R.id.editTextAddress),
            findViewById(R.id.editTextBirthday),
            findViewById(R.id.editTextContactNo),
            findViewById(R.id.editTextCourseAndYear),
            findViewById(R.id.editTextHeight),
            findViewById(R.id.editTextWeight)
        )

        button = findViewById(R.id.button)

        spinner = findViewById(R.id.spinner)
        val units = listOf("cm", "m", "ft")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner2 = findViewById(R.id.spinner2)
        val units2 = listOf("kg", "lbs")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, units2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        button.setOnClickListener{
            val name = editTexts[0].text.toString()
            val address = editTexts[1].text.toString()
            val birthday = editTexts[2].text.toString()
            val contactNo = editTexts[3].text.toString()
            val courseAndYear = editTexts[4].text.toString()
            val height = editTexts[5].text.toString()
            val weight = editTexts[6].text.toString()
            val heightUnit = spinner.selectedItem.toString()
            val weightUnit = spinner2.selectedItem.toString()

            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("name", name)
                putExtra("address", address)
                putExtra("birthday", birthday)
                putExtra("contactNo", contactNo)
                putExtra("courseAndYear", courseAndYear)
                putExtra("height", height)
                putExtra("weight", weight)
                putExtra("heightUnit", heightUnit)
                putExtra("weightUnit", weightUnit)
            }
            startActivity(intent)
        }
    }
}