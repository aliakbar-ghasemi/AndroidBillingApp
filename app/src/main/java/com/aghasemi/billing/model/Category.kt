package com.aghasemi.billing.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val parentId: Int,
    /**
     * 1:income
     * 2:outcome
     * */
    val type: Int
)