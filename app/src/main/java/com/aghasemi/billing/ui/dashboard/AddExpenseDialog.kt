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
    private var callback: Callback? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogAddExpenseBinding.inflate(LayoutInflater.from(context))
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        return builder.create();
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddExpenseBinding.inflate(inflater)
        /*val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(binding.root)*/
        setupUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {

        binding.btnAddExpense.setOnClickListener {
            //val expense: Expense? = binding.expense
            val expense = Expense(
                title = binding.edtTitle.text.toString(),
                date = binding.edtdate.text.toString(),
                amount = binding.edtAmount.text.toString(),
                categoryId = binding.edtCategory.getCategory()?.id
            )
            Log.d("##TAG", "setupUI: expense:" + expense)

            val category = binding.edtCategory.getCategory()
            Log.d("##AddExpenseDialog", "setupUI: " + category?.title)

            if (expense == null){
                Toast.makeText(context, "please enter correct info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (expense.title.isEmpty()){
                Toast.makeText(context, "please enter correct info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            callback?.addExpense(expense)
            dismiss()
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun addExpense(expense: Expense)
    }
}