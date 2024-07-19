package com.aghasemi.billing.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var categoryId: Int,
    var amount: String,
    var date: String
)