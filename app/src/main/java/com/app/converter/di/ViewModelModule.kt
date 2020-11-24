package com.app.converter.di

import com.app.converter.manager.base.BaseViewModel
import com.app.converter.ui.convert.ConvertRatesSheetViewModel
import com.app.converter.ui.home.HomeViewModel

import com.app.converter.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {


    viewModel {
        BaseViewModel()
    }
    viewModel {
        SplashViewModel()
    }

    viewModel {
        HomeViewModel()
    }

    viewModel {
        ConvertRatesSheetViewModel()
    }

}