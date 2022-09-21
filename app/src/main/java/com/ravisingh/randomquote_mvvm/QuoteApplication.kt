package com.ravisingh.randomquote_mvvm

import android.app.Application
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.ravisingh.randomquote_mvvm.api.QuoteService
import com.ravisingh.randomquote_mvvm.api.RetrofitHelper
import com.ravisingh.randomquote_mvvm.db.QuoteDatabase
import com.ravisingh.randomquote_mvvm.repository.QuoteRepository
import com.ravisingh.randomquote_mvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit
import androidx.work.Constraints
import androidx.work.WorkManager

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
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