package com.aghasemi.billing.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val title: String,
    val parentId: Int = -1,
    val type: Type
) {
    enum class Type {
        Income, Outcome
    }
}