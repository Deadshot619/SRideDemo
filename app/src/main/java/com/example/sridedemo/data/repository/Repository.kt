package com.example.sridedemo.data.repository

import android.util.Log
import com.example.sridedemo.data.remote.APIClient
import com.example.sridedemo.data.remote.api.ApiInterface
import com.example.sridedemo.model.State
import com.example.sridedemo.model.network.ArticleRequestModel
import com.example.sridedemo.model.network.Articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository() {
    private val mServices: ApiInterface by lazy {
        APIClient.getClient().create(ApiInterface::class.java)
    }

    fun getEverything(articleRequestModel: ArticleRequestModel) =
        flow<State<Articles>> {
            emit(State.loading())

            val result = mServices.getEverythingApiAsync(
                query = articleRequestModel.query,
                from = articleRequestModel.from,
                sortBy = articleRequestModel.sortBy,
                publishedAt = articleRequestModel.publishedAt,
                apiKey = articleRequestModel.apiKey
            ).await()

            emit(State.success(result))
        }.catch {
            // If exception is thrown, emit failed state along with message.
            Log.e("EverestekError", it.message.toString())
            emit(State.failed(it.message.toString()))
        }.flowOn(Dispatchers.IO)
}