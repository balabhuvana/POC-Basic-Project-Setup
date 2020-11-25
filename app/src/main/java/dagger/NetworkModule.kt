package dagger

import network.UserApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule() {
    @Singleton
    @Provides
    fun getApiInterface(retroFit: Retrofit): UserApiWebService {
        retrofit.baseUrl()
        return retroFit.create(UserApiWebService::class.java)
    }

    @get:Provides
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}