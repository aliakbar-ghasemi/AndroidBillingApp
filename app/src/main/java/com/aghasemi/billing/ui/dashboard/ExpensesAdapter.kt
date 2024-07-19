package com.aghasemi.billing.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aghasemi.billing.databinding.ItemExpenseBinding
import com.aghasemi.billing.model.Expense


class ExpensesAdapter : RecyclerView.Adapter<ExpensesAdapter.VH>() {
    private var list = emptyList<Expense>()

    fun setList(list: List<Expense>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context))
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class VH(private var binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Expense) {
            binding.txtTitle.text = item.title
        }

    }
}