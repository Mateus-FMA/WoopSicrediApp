package com.example.woopsicrediapp.core.app

import android.app.Application
import com.example.woopsicrediapp.core.appModule
import org.koin.android.ext.android.startKoin

class WoopSicrediApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}