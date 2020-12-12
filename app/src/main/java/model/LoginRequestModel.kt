package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequestModel {
    @SerializedName("name")
    @Expose
    var userName: String? = null

    @SerializedName("password")
    @Expose
    var userPassword: String? = null
}