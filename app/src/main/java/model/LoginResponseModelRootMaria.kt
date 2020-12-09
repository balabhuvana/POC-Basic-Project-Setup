package model

import com.google.gson.annotations.SerializedName

class LoginResponseModelRootMaria {
    @SerializedName("data")
    var data: LoginResponseModelMaria? = null

    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("message")
    var message: String? = null
}