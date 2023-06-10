package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.db.ShoppingDatabase
import com.example.myapplication.data.repositories.ShoppingRepository
import com.example.myapplication.ui.shoppinglist.ShoppingViewModel
import com.example.myapplication.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

//14 kodein
class ShoppingApplication:Application(),KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))
        bind() from singleton {
            ShoppingDatabase(instance())
        }
        bind() from singleton {
            ShoppingRepository(instance())
        }
        bind() from provider {
            ShoppingViewModelFactory(instance())
        }

    }
}