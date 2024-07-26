package com.aghasemi.billing.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aghasemi.billing.databinding.FragmentDashboardBinding
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.model.ExpenseAndCategory

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getExpenseList()

        binding.btnAdd.setOnClickListener {
            showAddExpenseDialog()
        }
    }

    private fun getExpenseList() {
        dashboardViewModel.getAllExpenseWithCategory()
            .observe(viewLifecycleOwner) { list ->
                Log.d("##TAG", "getExpenseList: "+list)
                updateExpenseList(list)
            }
    }

    private fun updateExpenseList(list: List<ExpenseAndCategory>) {
        val adapter = ExpensesAdapter()
        adapter.setList(list)
        binding.rvExpenses.adapter = adapter
    }

    private fun showAddExpenseDialog() {
        if (context == null) {
            return
        }

        val dialog = AddExpenseDialog()
        dialog.setCallback(object : AddExpenseDialog.Callback {
            override fun addExpense(expense: Expense) {
                dashboardViewModel.insertExpense(expense)
            }
        })

        dialog.show(childFragmentManager, "AddExpenseDialog")
    }
}