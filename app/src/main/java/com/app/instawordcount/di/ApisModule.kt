package com.app.instawordcount.di

import com.app.instawordcount.manager.db.WordRepository
import com.app.instawordcount.ui.home.HomeRepository
import org.koin.dsl.module


val apiModule = module {

    single {
        HomeRepository()
    }

    single {
        WordRepository(get())
    }

}