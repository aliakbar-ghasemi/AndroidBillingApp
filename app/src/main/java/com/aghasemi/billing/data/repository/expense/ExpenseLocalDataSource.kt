package com.aghasemi.billing.data.repository.expense

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.data.local.db.AppDatabase
import com.aghasemi.billing.data.local.db.ExpenseDao
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.model.ExpenseAndCategory

class ExpenseLocalDataSource(application: Application) {

    private val expenseDao:ExpenseDao

    init {
        val db = AppDatabase.getInstance(application)
        expenseDao = db.expenseDao()
    }

    fun getExpenseList(): LiveData<List<Expense>> {
        //read from db
        return expenseDao.getAll()
    }
    fun getAllExpenseWithCategory(): LiveData<List<ExpenseAndCategory>> {
        return expenseDao.getAllExpenseWithCategory()
    }

    fun insertExpense(expense: Expense){
        return expenseDao.insert(expense)
    }
    fun deleteExpense(){

    }
}