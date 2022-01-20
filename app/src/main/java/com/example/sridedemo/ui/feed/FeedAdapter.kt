package com.example.sridedemo.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sridedemo.databinding.ItemFeedBinding
import com.example.sridedemo.model.network.ArticlesItem

class FeedAdapter: ListAdapter<ArticlesItem, FeedAdapter.FeedViewHolder>(DiffUtils) {
    companion object DiffUtils : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(
            oldItem: ArticlesItem,
            newItem: ArticlesItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class FeedViewHolder(val binding: ItemFeedBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ArticlesItem){
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view =ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}