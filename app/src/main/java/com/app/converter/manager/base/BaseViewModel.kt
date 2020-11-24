package com.app.converter.manager.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel : ViewModel(), KoinComponent {
    val responseManager: ResponseManager by inject()
    val disposable :CompositeDisposable by inject ()
    val application :Application by inject ()
    var isUserAuthenticated = MutableLiveData<Boolean>()
    var observeError = MutableLiveData<String>()

}