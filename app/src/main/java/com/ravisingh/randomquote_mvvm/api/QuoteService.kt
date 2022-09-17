package com.ravisingh.randomquote_mvvm.api

import com.ravisingh.randomquote_mvvm.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("Pages") page: Int): Response<QuoteList>

}