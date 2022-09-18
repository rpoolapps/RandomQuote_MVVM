package com.ravisingh.randomquote_mvvm

import android.app.Application
import com.ravisingh.randomquote_mvvm.api.QuoteService
import com.ravisingh.randomquote_mvvm.api.RetrofitHelper
import com.ravisingh.randomquote_mvvm.db.QuoteDatabase
import com.ravisingh.randomquote_mvvm.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        // get Object of quoteService
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        // get Database object
        val database = QuoteDatabase.getDatabase(applicationContext)

        // get Instance of repository
        quoteRepository = QuoteRepository(quoteService,database,applicationContext)
    }
}