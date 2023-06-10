package com.example.myapplication.data.repositories

import com.example.myapplication.data.db.Entity.ShoppingItem
import com.example.myapplication.data.db.ShoppingDatabase
//repo 4
class ShoppingRepository(private val db : ShoppingDatabase) {
    fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItem()
}