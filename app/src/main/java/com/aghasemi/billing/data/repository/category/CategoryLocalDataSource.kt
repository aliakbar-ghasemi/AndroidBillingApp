package com.aghasemi.billing.data.repository.category

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.data.local.db.AppDatabase
import com.aghasemi.billing.data.local.db.CategoryDao
import com.aghasemi.billing.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryLocalDataSource(application: Application) {

    private val categoryDao:CategoryDao

    init {
        val db = AppDatabase.getInstance(application)
        categoryDao = db.categoryDao()
    }

    fun getCategoryListWithType(categoryType: String): LiveData<List<Category>> {
        //read from db
        return categoryDao.getAllWithType(categoryType)
    }

    suspend fun getAllSuspend(): List<Category> {
        return categoryDao.getAllSuspend()
    }

    fun insertCategory(category: Category){
        return categoryDao.insert(category)
    }
    fun deleteCategory(){

    }
}