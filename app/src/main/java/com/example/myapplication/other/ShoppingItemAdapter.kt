package com.example.myapplication.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.db.Entity.ShoppingItem
import com.example.myapplication.databinding.ShoppingItemBinding
import com.example.myapplication.ui.shoppinglist.ShoppingViewModel
//11
class ShoppingItemAdapter(var items : List<ShoppingItem>,private var viewModel: ShoppingViewModel)
    : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>()  {

    inner class ShoppingViewHolder (val binding: ShoppingItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoppingItemBinding.inflate(layoutInflater,parent,false)
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.binding.apply{
            tvName.text = curShoppingItem.name
            tvAmount.text = "${curShoppingItem.amount}"

            ivDelete.setOnClickListener {
                viewModel.delete(curShoppingItem)
            }
            ivMinus.setOnClickListener {
                if (curShoppingItem.amount >0){
                    curShoppingItem.amount--
                    viewModel.upsert(curShoppingItem)
                }
            }
            ivPlus.setOnClickListener {
                curShoppingItem.amount++
                viewModel.upsert(curShoppingItem)
            }
        }
    }
}