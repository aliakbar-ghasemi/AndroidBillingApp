package com.aghasemi.billing.utils

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TextInputEditTextBindingUtil {

    /*@BindingAdapter("validation2")
    fun TextInputLayout.setCustomText(item: String){

    }*/

    @BindingAdapter("app:validation")
    fun <T> setErrorEnable(textInputLayout: TextInputLayout, _validations: T) {
        if (textInputLayout.editText == null) return
        val validations: HashMap<Rule.StringRule, String> = HashMap<Rule.StringRule, String>()
        try {
            validations.putAll((_validations as HashMap<Rule.StringRule, String>))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val isErrorEnabled = booleanArrayOf(false)
        val errorText = arrayOf("")
        textInputLayout.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                errorText[0] = ""
                for (key in validations.keys) {
                    val hasError: Boolean = key.validate(textInputLayout.editText!!.text)
                    if (hasError) {
                        isErrorEnabled[0] = true
                        errorText[0] =
                            errorText[0] + (if (errorText[0].isEmpty()) "" else " , ") + getStringResourceByName(
                                textInputLayout.context,
                                validations[key]
                            )
                    }
                }
                textInputLayout.isErrorEnabled = isErrorEnabled[0]
                textInputLayout.error = errorText[0]
            }
        })
        textInputLayout.editText!!.onFocusChangeListener =
            OnFocusChangeListener { view: View?, hasFocus: Boolean ->
                if (!hasFocus) {
                    errorText[0] = ""
                    for (key in validations.keys) {
                        val hasError: Boolean =
                            key.validate(textInputLayout.editText!!.text)
                        if (hasError) {
                            isErrorEnabled[0] = true
                            errorText[0] = errorText[0] + (if (errorText[0]
                                    .isEmpty()
                            ) "" else " , ") + getStringResourceByName(
                                textInputLayout.context,
                                validations[key]
                            )
                        }
                    }
                    textInputLayout.isErrorEnabled = isErrorEnabled[0]
                    textInputLayout.error = errorText[0]
                }
            }
    }

    private fun getStringResourceByName(context: Context, aString: String?): String {
        try {
            val packageName = context.packageName
            val resId = context.resources.getIdentifier(aString, "string", packageName)
            return context.getString(resId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    object Rule {
        var REQUIRED = object : StringRule {
            override fun validate(s: Editable?): Boolean {
                return TextUtils.isEmpty(s.toString())
            }
        }
        var EMAIL: StringRule = object : StringRule {
            override fun validate(s: Editable?): Boolean {
                return s.toString().length < 18
            }
        }
        fun createRule(jsonString: String?): HashMap<StringRule, String?> {
            val result = HashMap<StringRule, String?>()
            val map = Gson().fromJson<HashMap<String, String>>(
                jsonString, object : TypeToken<HashMap<String?, String?>?>() {}.type
            )
            for (key in map.keys) {
                when (key) {
                    "required" -> result[REQUIRED] = map[key]
                    "email" -> result[EMAIL] = map[key]
                }
            }
            return result
        }

        interface StringRule {
            fun validate(s: Editable?): Boolean
        }
    }
}

