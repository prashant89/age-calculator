package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker();
        }
    }

  private  fun clickDatePicker() {
        val myCalendra = Calendar.getInstance()
        val year = myCalendra.get(Calendar.YEAR)
        val month = myCalendra.get(Calendar.MONTH)
        var day = myCalendra.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedyear, selectedmonth, selecteddayofMonth ->
                val selectedDate = "$selecteddayofMonth/${selectedmonth + 1}/$selectedyear"
                tvSelectedDate?.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 6000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 6000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

}