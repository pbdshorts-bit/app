package com.mapyourmove.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.mapyourmove.app.data.preferences.AppSettings

@HiltAndroidApp
class MapYourMoveApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app settings and configurations
        AppSettings.initialize(this)
    }
}
