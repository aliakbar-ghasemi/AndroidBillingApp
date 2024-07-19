package com.aghasemi.billing.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.aghasemi.billing.model.Expense

@Dao
interface ExpenseDao : BaseDao<Expense> {
    @Query("SELECT * FROM expense")
    fun getAll(): LiveData<List<Expense>>
}