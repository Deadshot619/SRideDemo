package com.example.sridedemo.ui.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sridedemo.R
import com.example.sridedemo.data.repository.Repository
import com.example.sridedemo.databinding.FragmentFeedBinding
import com.example.sridedemo.model.State
import com.example.sridedemo.ui.base.BaseFragment
import com.example.sridedemo.utils.extensions.showToast

class FeedFragment : BaseFragment<FeedViewModel, FragmentFeedBinding>() {

    private val repository = Repository()

    override val mViewModel: FeedViewModel by viewModels{ FeedViewModelFactory(repository) }
    override fun getViewBinding() = FragmentFeedBinding.inflate(layoutInflater)

    private lateinit var mRvMain : RecyclerView
    private lateinit var mAdapter: FeedAdapter

    override fun onBinding() {
        initViews()
        setUpRecyclerView()
        setUpObservers()
    }

    private fun initViews() {
        mViewBinding.run {
            lifecycleOwner = viewLifecycleOwner
            mRvMain = rvMain
            initProgressBar(layoutProgress)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = FeedAdapter()
        val linearLayoutManager = LinearLayoutManager(requireContext())
        mRvMain.apply {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpObservers() {
        mViewModel.feedData.observe(viewLifecycleOwner, {
            it.contentIfNotHandled?.let {
                when(it){
                    is State.Loading -> {
                        showProgressBar()
                    }
                    is State.Success -> {
                        mAdapter.submitList(it.data.articles)
                        showToast(it.data.articles.size.toString())
                        showProgressBar(false)
                    }
                    is State.Failed -> {
                        showToast(it.message)
                        showProgressBar(false)
                    }
                }
            }
        })
    }
}
