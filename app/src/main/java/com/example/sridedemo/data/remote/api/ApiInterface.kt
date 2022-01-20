package com.example.sridedemo.data.remote.api

import com.example.sridedemo.model.network.Articles
import com.example.sridedemo.utils.EVERYTHING
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET(EVERYTHING)
    fun getEverythingApiAsync(
        @Query(QUERY) query: String,
        @Query(FROM) from: String,
        @Query(SORT_BY) sortBy: String,
        @Query(PUBLISHED_AT) publishedAt: String,
        @Query(API_KEY) apiKey: String,
    ): Deferred<Articles>

    companion object {
        private const val QUERY = "q"
        private const val FROM = "from"
        private const val SORT_BY = "sortBy"
        private const val PUBLISHED_AT = "publishedAt"
        private const val API_KEY = "apiKey"

    }
}