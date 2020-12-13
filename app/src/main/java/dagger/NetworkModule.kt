package dagger

import network.LoginApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule() {

    private val BASE_URL =
        "https://62jnymp7n4.execute-api.us-east-2.amazonaws.com/Prod/api/CustomerLogin/"


    @Singleton
    @Provides
    fun getLoginApiInterface(retroFit: Retrofit): LoginApiWebService {
        retrofit.baseUrl()
        return retroFit.create(LoginApiWebService::class.java)
    }

    @get:Provides
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}