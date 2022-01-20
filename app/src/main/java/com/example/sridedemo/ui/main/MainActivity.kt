package com.example.sridedemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.sridedemo.R
import com.example.sridedemo.databinding.ActivityMainBinding
import com.example.sridedemo.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onBinding() {
        setUpNavigationAndActionBar()
    }

    private fun setUpNavigationAndActionBar() {
        setSupportActionBar(mViewBinding.toolbar)
        mNavController = this.findNavController(R.id.nav_host_fragment)
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph)

        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration)
    }
}