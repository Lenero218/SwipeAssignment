package com.example.swipeassessment

import android.app.Application
import com.example.swipeassessment.module.daoModule
import com.example.swipeassessment.module.dataSourceModule
import com.example.swipeassessment.module.databaseModule
import com.example.swipeassessment.module.mapperModule
import com.example.swipeassessment.module.networkModule
import com.example.swipeassessment.module.repoModule
import com.example.swipeassessment.module.usecaseModule
import com.example.swipeassessment.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class app : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@app)
            modules(listOf(daoModule, networkModule, databaseModule, dataSourceModule, repoModule, viewModelModule,
                usecaseModule, mapperModule))
        }
    }
}