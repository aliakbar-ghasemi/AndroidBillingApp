package com.aghasemi.billing.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.aghasemi.billing.model.Category

@Dao
interface CategoryDao : BaseDao<Category> {
    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM category where type=:type")
    fun getAllWithType(type: String): LiveData<List<Category>>

}