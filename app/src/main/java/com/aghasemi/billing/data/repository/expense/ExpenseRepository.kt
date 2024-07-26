package com.aghasemi.billing.data.repository.expense

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.model.ExpenseAndCategory

class ExpenseRepository(application: Application) {
    private val expenseLocalDataSource: ExpenseLocalDataSource

    init {
        expenseLocalDataSource = ExpenseLocalDataSource(application)
    }

    fun getExpenseList(): LiveData<List<Expense>> {
        return expenseLocalDataSource.getExpenseList()
    }
    fun getAllExpenseWithCategory(): LiveData<List<ExpenseAndCategory>> {
        return expenseLocalDataSource.getAllExpenseWithCategory()
    }

    fun insertExpense(expense: Expense) {
        return expenseLocalDataSource.insertExpense(expense)
    }
}