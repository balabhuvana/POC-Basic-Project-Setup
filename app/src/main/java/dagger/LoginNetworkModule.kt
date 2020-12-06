package dagger

import network.LoginApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginNetworkModule {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://reqres.in/"

    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getAPIService(): LoginApiWebService? {
        return getClient()?.create(LoginApiWebService::class.java)
    }

}