package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var selectedDateView: TextView? = null
  private  var displayTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val selectDateButton = findViewById<Button>(R.id.selectDate)
        selectedDateView = findViewById(R.id.selectDateView)
        displayTextView = findViewById(R.id.ageInMinutes)

        selectDateButton.setOnClickListener {
            selectDate()


        }
    }

    private fun selectDate() {
        val myCalender = Calendar.getInstance()
        val calendar = Calendar.getInstance()
        // Set the desired start year
        calendar.set(Calendar.YEAR, 2020)
        calendar.set(Calendar.MONTH, Calendar.JANUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
       val dialog= DatePickerDialog(
            this,
             { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "$selectedYear-$selectedMonth-$selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDates = "$selectedYear/$selectedMonth/$selectedDayOfMonth"
               selectedDateView?.text = selectedDates
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDates)
                theDate?.let {
                    val dateInMinutes=theDate.time/60000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentTimeInMinutes=currentDate.time/60000
                        val diff=currentTimeInMinutes-dateInMinutes
                        displayTextView?.text=diff.toString()
                    }
                }
            },
            year,
            month,
            dayOfMonth


        )

        dialog.datePicker.minDate = calendar.timeInMillis
        dialog.datePicker.maxDate = myCalender.timeInMillis
        dialog.show()
    }
}