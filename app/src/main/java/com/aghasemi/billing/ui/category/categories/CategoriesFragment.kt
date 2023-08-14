package com.aghasemi.billing.ui.category.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aghasemi.billing.databinding.FragmentCategoriesBinding
import com.aghasemi.billing.model.Category

class CategoriesFragment : Fragment() {
    companion object {
        fun newInstance(categoryType: String): CategoriesFragment {
            val fragment = CategoriesFragment()
            val bundle = Bundle()
            bundle.putString("categoryType", categoryType)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var binding: FragmentCategoriesBinding? = null

    private lateinit var categoriesViewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        categoriesViewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    private fun updateUi() {
        val bundle = arguments
        val categoryType = bundle?.getString("categoryType")
        if (categoryType != null) {
            getCategoryList(categoryType)
        }
    }

    private fun getCategoryList(categoryType: String) {
        categoriesViewModel.getCategoryList(categoryType).observe(viewLifecycleOwner) { categoryList ->
            updateCategoryList(categoryList)
        }
    }

    private fun updateCategoryList(categoryList: List<Category>) {
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.setCategories(categoryList)
        binding?.rvCategoryList?.adapter = categoriesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}