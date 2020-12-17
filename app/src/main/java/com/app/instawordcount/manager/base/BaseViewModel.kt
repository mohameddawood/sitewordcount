package com.app.instawordcount.manager.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.instawordcount.manager.db.WordRepository
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {
    var application :Application ? = null
    var showLoadingProcess = MutableLiveData<Boolean>()
    var wordRepository: WordRepository?=null
    var isInternetAvailable =false
    init {
        showLoadingProcess.value = false
    }

}