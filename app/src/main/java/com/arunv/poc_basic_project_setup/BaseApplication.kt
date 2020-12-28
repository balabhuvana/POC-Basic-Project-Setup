package com.arunv.poc_basic_project_setup

import android.app.Application
import dagger.*
import network.NetworkBuildConfig


open class BaseApplication : Application() {
    open var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(NetworkBuildConfig.BASE_URL))
            .roomModule(RoomModule(this))
            .build()
    }

}