package com.example.sridedemo.model.network

import com.example.sridedemo.BuildConfig
import com.example.sridedemo.data.remote.api.ApiInterface
import retrofit2.http.Query

data class ArticleRequestModel(
    val query: String = "tesla",
    val from: String = "",
    val sortBy: String = "",
    val publishedAt: String = "",
    val apiKey: String = BuildConfig.API_KEY,
)
