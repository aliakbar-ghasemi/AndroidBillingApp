package com.aghasemi.billing.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aghasemi.billing.data.repository.expense.ExpenseRepository
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.model.ExpenseAndCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseRepository: ExpenseRepository

    init {
        expenseRepository = ExpenseRepository(application)
    }

    fun getAllExpenseWithCategory(): LiveData<List<ExpenseAndCategory>> {
        return expenseRepository.getAllExpenseWithCategory()
    }

    fun insertExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            expenseRepository.insertExpense(expense)
        }
    }
}