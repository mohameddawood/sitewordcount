package com.app.converter.di

import com.app.converter.manager.base.ResponseManager
import com.app.converter.manager.connection.Resource
import com.app.converter.model.entities.ConvertRateRequest
import com.app.converter.ui.home.HomeRepository
import org.koin.dsl.module


val apiModule = module {

    single {
        HomeRepository()
    }

    factory {
        ConvertRateRequest()
    }

    single {
        ResponseManager(Resource(), get())
    }

}