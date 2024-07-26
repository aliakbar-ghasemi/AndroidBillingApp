package com.aghasemi.billing.data.repository.category

import android.app.Application
import androidx.lifecycle.LiveData
import com.aghasemi.billing.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(application: Application) {
    private val categoryLocalDataSource : CategoryLocalDataSource

    init {
        categoryLocalDataSource = CategoryLocalDataSource(application)
    }

    fun getCategoryListWithType(categoryType: String): LiveData<List<Category>> {
        return categoryLocalDataSource.getCategoryListWithType(categoryType)
    }

    fun insertCategory(category: Category){
        return categoryLocalDataSource.insertCategory(category)
    }

    suspend fun getAllSuspendCategories(): List<Category> {
        return categoryLocalDataSource.getAllSuspend()
    }
}