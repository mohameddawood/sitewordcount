package com.app.converter.ui.convert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.converter.R
import com.app.converter.manager.base.BaseViewModel
import com.app.converter.manager.connection.Resource
import com.app.converter.manager.utilities.Validation
import com.app.converter.model.entities.ConvertRateRequest
import com.app.converter.ui.home.HomeRepository
import org.koin.core.inject

class ConvertRatesSheetViewModel : BaseViewModel() {
    val homeRepository: HomeRepository by inject()
    var observeCloseClick = MutableLiveData<Boolean>()
    var observSucces = MutableLiveData<Boolean>()
    var request:ConvertRateRequest?=null

    fun convertRate(request: ConvertRateRequest) {
        if (validateRate(request.amount))
            getRepositoryData(request)
    }

    private fun validateRate(email: String): Boolean {
        var isValid = true
        if (Validation.isNullOrEmpty(email)) {
            observeError.value = "please add value"
            isValid = false
        }
        return isValid
    }

    fun getRepositoryData(request: ConvertRateRequest) {
        responseManager.loading()
        disposable.add(
                homeRepository.convertRate(from = request.from,to = request.to,amount = request.amount.toDouble()).subscribe({ data ->
                    if (data == null) responseManager.failed("Error")
                    else {
                        observSucces.value = true
                    }
                    responseManager.hideLoading()
                }, {
                    responseManager.hideLoading()
                    responseManager.failed(it.message)
                })
        )
    }

    fun onCloseClicked() {
        observeCloseClick.value = true
    }

    val closeClick: LiveData<Boolean>?
        get() {
            if (observeCloseClick != null) observeCloseClick = MutableLiveData()
            return observeCloseClick
        }
}