package com.aghasemi.billing.ui.category.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.aghasemi.billing.data.repository.CategoryRepository
import com.aghasemi.billing.model.Category

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryRepository: CategoryRepository

    init {
        categoryRepository = CategoryRepository(application)
    }

    fun getCategoryList(categoryType: String): LiveData<List<Category>> {
        return categoryRepository.getCategoryList(categoryType)
    }
}