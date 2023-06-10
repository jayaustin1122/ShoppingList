package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.db.Entity.ShoppingItem
import com.example.myapplication.data.db.ShoppingDatabase
import com.example.myapplication.data.repositories.ShoppingRepository
import com.example.myapplication.databinding.ActivityShoppingBinding
import com.example.myapplication.other.ShoppingItemAdapter
import com.example.myapplication.ui.shoppinglist.ShoppingViewModel
import com.example.myapplication.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
//import kodein
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

// 6                                        import //15
class ShoppingActivity : AppCompatActivity() ,KodeinAware{
    //16
    override val kodein by kodein()
    private val factory : ShoppingViewModelFactory by instance()

    private lateinit var binding : ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

            //init objects and pass datas

            //remove this to use in kodein
//        //8
//        val database = ShoppingDatabase(this)
//        //9
//        val repository = ShoppingRepository(database)
//        //10
//        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        //13
        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter
        //calling this coming from Dao
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingDialog(this,
                object : AddDialogListener{
                    override fun addButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }

                }).show()
        }
    }
}