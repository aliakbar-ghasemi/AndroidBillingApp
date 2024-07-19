package com.aghasemi.billing.ui.category.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aghasemi.billing.data.repository.category.CategoryRepository
import com.aghasemi.billing.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val categoryRepository: CategoryRepository

    init {
        categoryRepository = CategoryRepository(application)
    }

    fun getCategoryList(categoryType: String): LiveData<List<Category>> {
        return categoryRepository.getCategoryList(categoryType)
    }

    fun insertCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.insertCategory(category)
        }
    }
}