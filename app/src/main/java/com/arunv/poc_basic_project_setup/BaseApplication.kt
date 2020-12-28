package com.arunv.poc_basic_project_setup

import android.app.Application
import dagger.*


class BaseApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule("https://62jnymp7n4.execute-api.us-east-2.amazonaws.com/Prod/api/CustomerLogin/"))
            .roomModule(RoomModule(this))
            .build()
    }

}