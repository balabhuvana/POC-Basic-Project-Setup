package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponseModelMaria {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("full_name")
    @Expose
    var full_name: String? = null

    @SerializedName("firstname")
    @Expose
    var firstname: String? = null

    @SerializedName("lastname")
    @Expose
    var lastname: String? = null

    @SerializedName("phonenumber")
    @Expose
    var phonenumber: Int? = null

    @SerializedName("emailid")
    @Expose
    var emailid: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("country_code")
    @Expose
    var country_code: String? = null
}