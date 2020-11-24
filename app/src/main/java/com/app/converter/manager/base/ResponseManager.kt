package com.app.converter.manager.base

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.app.converter.manager.connection.Resource

data class ResponseManager constructor(
    private val resource: Resource<Any>,
    private val sharedPreferences: SharedPreferences
){

    private val responseManager = MutableLiveData<Resource<Any>>()


    fun loading() { setResponseObject(Resource.ResourceStatus.LOADING, null) }
    fun hideLoading() { setResponseObject(Resource.ResourceStatus.HIDE_LOADING, null) }
    fun success(message: String?) { setResponseObject(Resource.ResourceStatus.SUCCESS, message) }
    fun failed(message: String?) { setResponseObject(Resource.ResourceStatus.FAILED, message) }
    fun noConnection() { setResponseObject(Resource.ResourceStatus.NO_CONNECTION, null) }
    fun logout() {
        setResponseObject(Resource.ResourceStatus.LOGOUT, null)
    }


    private fun setResponseObject(status: Resource.ResourceStatus, message: String?){
        resource.resourceStatus = status
        responseManager.value = resource
    }

    fun getResponseManager() =   responseManager


}