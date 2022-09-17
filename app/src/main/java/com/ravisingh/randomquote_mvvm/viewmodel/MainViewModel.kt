package com.ravisingh.randomquote_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravisingh.randomquote_mvvm.models.QuoteList
import com.ravisingh.randomquote_mvvm.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<QuoteList>
    get() = repository.quotes
}