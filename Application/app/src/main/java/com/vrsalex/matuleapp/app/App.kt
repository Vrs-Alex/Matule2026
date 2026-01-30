package com.vrsalex.matuleapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()


        val config = AppMetricaConfig.newConfigBuilder("<API>").build()
        AppMetrica.activate(this, config)
    }
}