package com.example.sridedemo.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sridedemo.data.repository.Repository
import com.example.sridedemo.model.Event
import com.example.sridedemo.model.State
import com.example.sridedemo.model.network.ArticleRequestModel
import com.example.sridedemo.model.network.Articles
import com.example.sridedemo.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeedViewModel(val mRepository: Repository) : BaseViewModel() {

    private var feedJob: Job? = null

    init {
        getFeed()
    }

    private val _feedData = MutableLiveData<Event<State<Articles>>>()
    val feedData: LiveData<Event<State<Articles>>>
        get() = _feedData

    fun getFeed(){
        feedJob?.cancel()
        feedJob = viewModelScope.launch {
            mRepository.getEverything(ArticleRequestModel()).collect {
                _feedData.value = Event(it)
            }
        }
    }
}