package com.exa.android.inventorymanagement.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exa.android.inventorymanagement.Models.Category
import com.exa.android.inventorymanagement.Models.Stock
import com.exa.android.inventorymanagement.databinding.CategoryItemBinding
import com.exa.android.inventorymanagement.databinding.StockItemBinding

class StockAdapter(val items : List<Stock>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StockItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: StockItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(stock : Stock){
            binding.price.text = stock.price.toString()
            binding.quantity.text = stock.quantity.toString()
            binding.stockName.text = stock.name
        }
    }
}