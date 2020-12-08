package dagger

import network.LoginApiWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginNetworkModule {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://reqres.in/"
    private val LOGIN_URL_MARIA_SERVER =
        "https://62jnymp7n4.execute-api.us-east-2.amazonaws.com/Prod/api/"

    private fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(LOGIN_URL_MARIA_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun getAPIService(): LoginApiWebService? {
        return getClient()?.create(LoginApiWebService::class.java)
    }

}