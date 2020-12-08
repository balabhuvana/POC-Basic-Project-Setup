package model

import com.google.gson.annotations.SerializedName

class LoginRequestModelMaria {
    @SerializedName("name")
    var userName: String? = null

    @SerializedName("password")
    var userPassword: String? = null
}