package com.ravisingh.randomquote_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ravisingh.randomquote_mvvm.api.QuoteService
import com.ravisingh.randomquote_mvvm.api.RetrofitHelper
import com.ravisingh.randomquote_mvvm.repository.QuoteRepository
import com.ravisingh.randomquote_mvvm.viewmodel.MainViewModel
import com.ravisingh.randomquote_mvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as QuoteApplication).quoteRepository

        // get ViewModel instance
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this) {
            //Log.d("RPOOL APPS", it.results.toString())
            Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}