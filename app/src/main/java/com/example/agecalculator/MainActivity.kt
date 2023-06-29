package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var selectedDateView: TextView? = null
    var displayTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectDateButton = findViewById<Button>(R.id.selectDate)
        selectedDateView = findViewById<TextView>(R.id.selectDateView)
        displayTextView = findViewById<TextView>(R.id.ageInMinutes)

        selectDateButton.setOnClickListener {
            selectDate()


        }
    }

    fun selectDate() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "$selectedYear-$selectedMonth-$selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDates = "$selectedYear/$selectedMonth/$selectedDayOfMonth"
                this.selectedDateView?.text = selectedDates
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDates)
                val dateInMinutes=theDate.time/60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentTimeInMinutes=currentDate.time/60000
                val diff=currentTimeInMinutes-dateInMinutes
                displayTextView?.text=diff.toString()
            },
            year,
            month,
            dayOfMonth


        ).show()
    }
}