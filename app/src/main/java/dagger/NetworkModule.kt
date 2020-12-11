package dagger

import network.LoginApiWebService
import network.UserApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule() {

    private val BASE_URL = "https://reqres.in/"

    private val LOGIN_URL_MARIA_SERVER =
        "https://62jnymp7n4.execute-api.us-east-2.amazonaws.com/Prod/api/CustomerLogin/"

    @Singleton
    @Provides
    fun getApiInterface(retroFit: Retrofit): UserApiWebService {
        retrofit.baseUrl()
        return retroFit.create(UserApiWebService::class.java)
    }


    @Singleton
    @Provides
    fun getLoginApiInterface(retroFit: Retrofit): LoginApiWebService {
        retrofit.baseUrl()
        return retroFit.create(LoginApiWebService::class.java)
    }

    @get:Provides
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(LOGIN_URL_MARIA_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}