package com.aghasemi.billing.ui.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aghasemi.billing.ui.category.categories.CategoriesFragment

class CategoryTypesAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {
    companion object {
        var typeList = arrayListOf(1, 2)
    }


    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoriesFragment.newInstance(typeList[position])
    }
}