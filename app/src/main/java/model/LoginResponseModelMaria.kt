package model

import com.google.gson.annotations.SerializedName

class LoginResponseModelMaria {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("message")
    var message: String? = null
}