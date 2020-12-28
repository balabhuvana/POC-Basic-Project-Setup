package com.arunv.poc_basic_project_setup_test

import com.arunv.poc_basic_project_setup.BaseApplication
import dagger.*

class UiTestApp() : BaseApplication() {
    override var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(TestNetworkModule("http://localhost:8080/"))
            .roomModule(RoomModule(this))
            .build()
    }
}