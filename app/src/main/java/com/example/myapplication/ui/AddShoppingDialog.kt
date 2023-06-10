package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.myapplication.data.db.Entity.ShoppingItem
import com.example.myapplication.databinding.DialogAddShoppingItemBinding

//12
class AddShoppingDialog(context: Context,var addDialogListener: AddDialogListener) : AppCompatDialog(context){
    private lateinit var binding : DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amountText = binding.etAmount.text.toString()

            if (name.isEmpty() || amountText.isEmpty()) {
                Toast.makeText(context, "Empty Fields Are Not Allowed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toIntOrNull()
            if (amount == null) {
                Toast.makeText(context, "Invalid Amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val itemData = ShoppingItem(name, amount)
            addDialogListener.addButtonClicked(itemData)
            dismiss()
        }
        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}