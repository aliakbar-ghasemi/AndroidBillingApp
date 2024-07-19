package com.aghasemi.billing.data.repository.category

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.data.repository.category.CategoryLocalDataSource
import com.aghasemi.billing.model.Category

class CategoryRepository(application: Application) {
    private val categoryLocalDataSource : CategoryLocalDataSource

    init {
        categoryLocalDataSource = CategoryLocalDataSource(application)
    }

    fun getCategoryList(categoryType: String): LiveData<List<Category>> {
        return categoryLocalDataSource.getCategoryList(categoryType)
    }

    fun insertCategory(category: Category){
        return categoryLocalDataSource.insertCategory(category)
    }
}