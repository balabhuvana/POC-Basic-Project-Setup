package network

import retrofit2.Call
import retrofit2.http.GET

interface UserApiWebService {
    @GET("api/users?page=2")
    fun getUserList(): Call<UserListModelRoot>
}