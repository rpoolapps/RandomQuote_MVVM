package com.ravisingh.randomquote_mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ravisingh.randomquote_mvvm.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("Select * from quote")
    suspend fun getQuotes() : List<Result>
}