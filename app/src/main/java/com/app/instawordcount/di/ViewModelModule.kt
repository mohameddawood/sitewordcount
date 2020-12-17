package com.app.instawordcount.di

import com.app.instawordcount.manager.base.BaseViewModel
import com.app.instawordcount.ui.home.HomeViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {


    viewModel {
        BaseViewModel()
    }

    viewModel {
        HomeViewModel()
    }

}