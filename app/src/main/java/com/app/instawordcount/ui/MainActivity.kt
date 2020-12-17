package com.app.instawordcount.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.app.instawordcount.R
import com.app.instawordcount.databinding.ActivityMainBinding
import com.app.instawordcount.manager.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}