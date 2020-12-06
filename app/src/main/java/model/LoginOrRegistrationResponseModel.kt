package model

import com.google.gson.annotations.SerializedName

class LoginOrRegistrationResponseModel(
) {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("token")
    var token: String? = null

    @SerializedName("error")
    var error: String? = null
}