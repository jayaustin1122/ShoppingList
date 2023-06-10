package com.example.myapplication.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.db.Entity.ShoppingItem
import com.example.myapplication.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
// viewmodel  5
class ShoppingViewModel(private val repository: ShoppingRepository):ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(item)
    }
    fun delete (item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }
    fun getAllShoppingItems() = repository.getAllShoppingItems()
}