package com.vrsalex.matuleapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()


        val config = AppMetricaConfig.newConfigBuilder("337f6742-93ed-4e34-9582-4662c51ce9ba").build()
        AppMetrica.activate(this, config)
    }
}