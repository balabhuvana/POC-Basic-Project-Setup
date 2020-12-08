package model

import com.google.gson.annotations.SerializedName

class LoginOrRegistrationRequestModel {

    @SerializedName("email")
    var email: String? = null

    @SerializedName("password")
    var password: String? = null

}