package com.example.sridedemo.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sridedemo.data.repository.Repository

class FeedViewModelFactory(private val mRepository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(mRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}