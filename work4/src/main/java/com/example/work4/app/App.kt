package com.example.work4.app

import android.app.Application
import com.example.work4.modules.AppComponent
import com.example.work4.modules.DaggerAppComponent

open class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().roomModule()
    }
}