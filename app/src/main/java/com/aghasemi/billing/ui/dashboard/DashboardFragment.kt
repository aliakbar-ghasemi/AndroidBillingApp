package com.aghasemi.billing.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aghasemi.billing.databinding.DialogAddCategoryBinding
import com.aghasemi.billing.databinding.DialogAddExpenseBinding
import com.aghasemi.billing.databinding.FragmentDashboardBinding
import com.aghasemi.billing.model.Category
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.ui.category.categories.CategoriesAdapter

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
        dashboardViewModel.getExpenseList()
            .observe(viewLifecycleOwner) { list ->
                updateExpenseList(list)
            }
    }

    private fun updateExpenseList(list: List<Expense>) {
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