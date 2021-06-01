package com.test.app.pin

import android.app.Application
import org.koin.core.context.startKoin


class PinApplication  : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }


}