package com.aghasemi.billing.model

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseAndCategory(
    @Embedded
    var expense: Expense,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    var category: Category?
)