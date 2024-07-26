package com.aghasemi.billing.widget

import android.content.Context
import android.content.DialogInterface
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.aghasemi.billing.App
import com.aghasemi.billing.R
import com.aghasemi.billing.data.repository.category.CategoryRepository
import com.aghasemi.billing.model.Category
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EditTextCategoryPicker : TextInputEditText {

    private var category : Category? = null
    private var categoryList = ArrayList<Category>()

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
        val categoryRepository = App.getInstance()?.let { CategoryRepository(it) }

        CoroutineScope(Dispatchers.Default).launch{
            categoryRepository?.getAllSuspendCategories()?.let { categoryList.addAll(it) }
        }
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (text.isBlank()) {
            updateDisplay(context.getString(R.string.pickCategory))
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
        Log.d("##edtCategoryPicker", "showCategoryPickerDialog: "+categoryList)

        val b: AlertDialog.Builder = AlertDialog.Builder(context)
        b.setTitle(context.getString(R.string.pickCategory))

        val types = categoryList.map { item -> item.title }.toTypedArray()

        b.setItems(types) { p0, p1 ->
            category = categoryList[p1]

            if (category != null){
                updateDisplay(category!!.title)
            }

        }

        b.show()
    }

    fun getCategory(): Category? {
        return category
    }

    private fun updateDisplay(text: CharSequence) {
        this.setText(text)
    }
}
