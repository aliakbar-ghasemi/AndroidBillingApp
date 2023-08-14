package com.aghasemi.billing.ui.category.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aghasemi.billing.databinding.ItemCategoryBinding
import com.aghasemi.billing.model.Category


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.VH>() {
    private var categories = emptyList<Category>()

    fun setCategories(categoryList: List<Category>) {
        categories = categoryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = categories[position]
        holder.bind(item)
    }

    class VH(private var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.txtTitle.text = item.title
        }

    }
}