package com.ravisingh.randomquote_mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravisingh.randomquote_mvvm.api.QuoteService
import com.ravisingh.randomquote_mvvm.db.QuoteDatabase
import com.ravisingh.randomquote_mvvm.models.QuoteList
import com.ravisingh.randomquote_mvvm.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {
        if (NetworkUtils.isNetworkAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            // created dummy object of quoteListData and return DB data
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quotesLiveData.postValue(quoteList)
        }


    }

    suspend fun getQuotesBackground() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(2)
        if (result?.body() != null) {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }
    }


}