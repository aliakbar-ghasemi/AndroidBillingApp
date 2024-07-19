package com.aghasemi.billing.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.aghasemi.billing.databinding.DialogAddExpenseBinding
import com.aghasemi.billing.model.Expense

class AddExpenseDialog : DialogFragment() {
    private lateinit var binding: DialogAddExpenseBinding
    private var callback : Callback? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddExpenseBinding.inflate(inflater)


        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.btnAddExpense.setOnClickListener {
            val expense: Expense? = binding.expense
            Log.d("##TAG", "setupUI: expense:"+expense?.date)
            /*if (title.isNotBlank()) {
                val expense = Expense(0, title, 1, "0", "")
                Log.d("##TAG", "setupUI: expense:"+expense)
                //callback?.addExpense(expense)
                dismiss()
            } else {
                Toast.makeText(context, "please enter correct info", Toast.LENGTH_SHORT).show()
            }*/
        }
    }
    fun setCallback(callback: Callback){
        this.callback = callback
    }
    public interface Callback{
        fun addExpense(expense: Expense)
    }
}