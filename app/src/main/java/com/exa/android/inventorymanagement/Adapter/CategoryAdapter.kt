package com.exa.android.inventorymanagement.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exa.android.inventorymanagement.Models.Category
import com.exa.android.inventorymanagement.databinding.CategoryItemBinding

class CategoryAdapter(val items : List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
           binding.categoryName.text = category.name
        }
    }
}