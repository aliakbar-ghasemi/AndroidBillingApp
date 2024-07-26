package com.aghasemi.billing.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.aghasemi.billing.model.Expense
import com.aghasemi.billing.model.ExpenseAndCategory

@Dao
interface ExpenseDao : BaseDao<Expense> {
    @Query("SELECT * FROM expense")
    fun getAll(): LiveData<List<Expense>>

    @Query("select * from expense")
    fun getAllExpenseWithCategory() : LiveData<List<ExpenseAndCategory>>
}