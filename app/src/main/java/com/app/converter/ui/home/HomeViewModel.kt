package com.app.converter.ui.home

import androidx.lifecycle.MutableLiveData
import com.app.converter.manager.base.BaseViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : BaseViewModel() ,KoinComponent{

    val repository:HomeRepository by inject ()
    val showAllRates = MutableLiveData<HashMap<String,Double>>()
    val showBaseRate = MutableLiveData<String>()

    fun getRates(){
        responseManager.loading()
        disposable.add(
            repository.getRates().subscribe({
                responseManager.hideLoading()
                if (it!=null){
                    showBaseRate.value = it.baseRate
                    if (it.data!=null){
                        showAllRates.value = it.data
                    }else responseManager.failed("Empty list")
                }else responseManager.failed("Empty list")
            }, {
            })
        )

    }
}