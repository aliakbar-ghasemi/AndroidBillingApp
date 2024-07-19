package com.aghasemi.billing.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EditTextCategoryPicker : TextInputEditText {

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

    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text.isBlank()) {

            //updateDisplay()
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            showCategoryPickerDialog()
        }
    }

    override fun performClick(): Boolean {
        showCategoryPickerDialog()
        return super.performClick()
    }

    private fun showCategoryPickerDialog() {

    }



    private fun updateDisplay(calendar: Calendar?) {
        val format = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        val str = simpleDateFormat.format(calendar!!.time)
        this.setText(str)
    }
}
