package com.example.movieapp.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.Calendar


fun Context.showDatePicker(
    calendar: Calendar,
    onDateSelected: (Int, Int, Int) -> Unit
) {
    DatePickerDialog(
       this,
        { _, year, month, day ->
            onDateSelected(year, month, day)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun Context.showTimePicker(
    calendar: Calendar,
    onTimeSelected: (Int, Int) -> Unit
) {
    TimePickerDialog(
        this,
        { _, hour, minute ->
            onTimeSelected(hour, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    ).show()
}