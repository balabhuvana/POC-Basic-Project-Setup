package com.arunv.poc_basic_project_setup_test

import dagger.Module
import dagger.NetworkModule
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class TestNetworkModule(
    private
    val baseUrl: String
) : NetworkModule(baseUrl) {

    @get:Provides
    override val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}