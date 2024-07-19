package com.aghasemi.billing.widget

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.DatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


class EditTextDatePicker : TextInputEditText, OnDateSetListener {
    private var calendar: Calendar? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        calendar = Calendar.getInstance(TimeZone.getDefault())
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text.isBlank()) {
            calendar = Calendar.getInstance(TimeZone.getDefault())
            updateDisplay(calendar)
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            showDatePickerDialog()
        }
    }

    override fun performClick(): Boolean {
        showDatePickerDialog()
        return super.performClick()
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            context, this,
            calendar!![Calendar.YEAR], calendar!![Calendar.MONTH], calendar!![Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar?.set(Calendar.YEAR, year)
        calendar?.set(Calendar.MONTH, monthOfYear)
        calendar?.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateDisplay(calendar)
    }

    private fun updateDisplay(calendar: Calendar?) {
        val format = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        val str = simpleDateFormat.format(calendar!!.time)
        this.setText(str)
    }
}
