package com.trendzio

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrendzioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}