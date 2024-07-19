package com.aghasemi.billing.ui.category.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aghasemi.billing.databinding.DialogAddCategoryBinding
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
        setupUi()
    }

    private fun setupUi() {
        val bundle = arguments
        val categoryType = bundle?.getString("categoryType")
        if (categoryType != null) {
            getCategoryList(categoryType)

            binding?.btnAdd?.setOnClickListener {
                val type = Category.Type.valueOf(categoryType)
                showAddCategoryDialog(type)
            }
        }


    }

    private fun showAddCategoryDialog(type: Category.Type) {
        if (context == null) {
            return
        }

        val addCategoryBinding = DialogAddCategoryBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(addCategoryBinding.root)
            .show()

        addCategoryBinding.btnAddCategory.setOnClickListener {
            val categoryName = addCategoryBinding.edtCategoryName.text.toString()
            if (categoryName.isNotBlank()) {
                val category = Category(0, categoryName, 0, type)
                categoriesViewModel.insertCategory(category)
                dialog.dismiss()
            } else {
                Toast.makeText(context, "please enter category name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCategoryList(categoryType: String) {
        categoriesViewModel.getCategoryList(categoryType)
            .observe(viewLifecycleOwner) { categoryList ->
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