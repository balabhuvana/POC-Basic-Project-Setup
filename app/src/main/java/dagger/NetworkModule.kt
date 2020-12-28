package dagger

import network.LoginApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class NetworkModule(private val baseUrl: String) {


    @Singleton
    @Provides
    fun getLoginApiInterface(retroFit: Retrofit): LoginApiWebService {
        retrofit.baseUrl()
        return retroFit.create(LoginApiWebService::class.java)
    }

    @get:Provides
    open val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}