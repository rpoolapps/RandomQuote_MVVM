package com.ravisingh.randomquote_mvvm.repository

import com.ravisingh.randomquote_mvvm.models.QuoteList

/*
sealed class Response(val data: QuoteList? = null, val errorMessage: String? = null) {

    class Loading : Response()
    class Success(quoteList: QuoteList) : Response(data = quoteList)
    class Error(errorMessage: String) : Response(errorMessage = errorMessage)

}*/

// Generic class for handling API response
sealed class Response<T>(val data: T? = null, val errorMessage: String? = null) {

    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)

}
