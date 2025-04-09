package com.ag.project

import android.app.Application
import com.ag.project.presentation.di.initKoin
import org.koin.android.ext.koin.androidContext

class FakeStoreApp:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@FakeStoreApp)
        }
    }
}