package com.example.myapplication.ui

import com.example.myapplication.data.db.Entity.ShoppingItem

//13
interface AddDialogListener {
    fun addButtonClicked(item: ShoppingItem )
}