package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponseModel {
    @SerializedName("data")
    @Expose
    var data: String? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}