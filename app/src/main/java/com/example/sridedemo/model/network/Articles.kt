package com.example.sridedemo.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("status") @Expose val status: String,
    @SerializedName("totalResults") @Expose val totalResults: Int,
    @SerializedName("articles") @Expose val articles: List<ArticlesItem>
)

data class ArticlesItem(
    @SerializedName("author") @Expose val author: String,
    @SerializedName("content") @Expose val content: String,
    @SerializedName("description") @Expose val description: String,
    @SerializedName("publishedAt") @Expose val publishedAt: String,
    @SerializedName("source") @Expose val source: Source,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("urlToImage") @Expose val urlToImage: String
)

data class Source(
    @SerializedName("id") @Expose val id: String,
    @SerializedName("name") @Expose val name: String
)