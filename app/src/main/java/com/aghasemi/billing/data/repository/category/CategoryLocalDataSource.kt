package com.aghasemi.billing.data.repository.category

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.data.local.db.AppDatabase
import com.aghasemi.billing.data.local.db.CategoryDao
import com.aghasemi.billing.model.Category

class CategoryLocalDataSource(application: Application) {

    private val categoryDao:CategoryDao

    init {
        val db = AppDatabase.getInstance(application)
        categoryDao = db.categoryDao()
    }

    fun getCategoryList(categoryType: String): LiveData<List<Category>> {
        //read from db
        return categoryDao.getAllWithType(categoryType)
    }

    fun insertCategory(category: Category){
        return categoryDao.insert(category)
    }
    fun deleteCategory(){

    }
}