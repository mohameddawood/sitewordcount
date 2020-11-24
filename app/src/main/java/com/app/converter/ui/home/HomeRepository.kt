package com.app.converter.ui.home

import com.app.converter.manager.connection.Api
import com.app.converter.manager.connection.AppSchedulerProvider
import com.app.converter.manager.connection.Resource
import com.app.converter.model.entities.ConvertRateResponse
import io.reactivex.Flowable
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeRepository:KoinComponent {
    val api: Api by inject()
    private val appSchedulerProvider: AppSchedulerProvider by inject()

    fun getRates(): Flowable<Resource<HashMap<String,Double>>> {
        return api.getRates().compose(appSchedulerProvider.ioToMainFlowableScheduler())
    }

    fun convertRate(from:String,to:String,amount:Double): Flowable<Resource<ConvertRateResponse>> {
        return api.convertRate(from = from,to = to,amount = amount).compose(appSchedulerProvider.ioToMainFlowableScheduler())
    }
}