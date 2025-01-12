    package com.example.bmicalculator

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

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

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            val view = currentFocus
            if (view is EditText) {
                val rect = Rect()
                view.getGlobalVisibleRect(rect)
                if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    view.clearFocus()  // Clear focus if the touch is outside the EditText

                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
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
            findViewById(R.id.editTextInches),
            findViewById(R.id.editTextWeight)
        )

        button = findViewById(R.id.button)

        spinner = findViewById(R.id.spinner)
        val units = listOf("cm", "m", "ft")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val inchesContainer: TextInputLayout = findViewById(R.id.editTextInchesContainer)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent?.getItemAtPosition(position).toString() == "ft") {
                    inchesContainer.visibility = View.VISIBLE
                } else {
                    inchesContainer.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Optionally handle the case where nothing is selected, like hiding the editText
                editTexts[6].visibility = View.GONE
            }
        }

        editTexts[5].setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && editTexts[5].text.isNullOrEmpty()) {
                editTexts[5].setText("0")  // Reset if empty
            }
        }

        editTexts[6].setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && editTexts[6].text.isNullOrEmpty()) {
                editTexts[6].setText("0")  // Reset if empty
            }
        }

        editTexts[7].setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && editTexts[7].text.isNullOrEmpty()) {
                editTexts[7].setText("0")  // Reset if empty
            }
        }

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
            val inches = editTexts[6].text.toString()
            val weight = editTexts[7].text.toString()
            val heightUnit = spinner.selectedItem.toString()
            val weightUnit = spinner2.selectedItem.toString()

            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("name", name)
                putExtra("address", address)
                putExtra("birthday", birthday)
                putExtra("contactNo", contactNo)
                putExtra("courseAndYear", courseAndYear)
                putExtra("height", height)
                putExtra("inches", inches)
                putExtra("weight", weight)
                putExtra("heightUnit", heightUnit)
                putExtra("weightUnit", weightUnit)
            }
            startActivity(intent)
        }
    }
}