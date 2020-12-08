package model

import com.google.gson.annotations.SerializedName

class LoginResponseModelMaria {
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("full_name")
    var full_name: String? = null

    @SerializedName("firstname")
    var firstname: String? = null

    @SerializedName("lastname")
    var lastname: String? = null

    @SerializedName("phonenumber")
    var phonenumber: Int? = null

    @SerializedName("emailid")
    var emailid: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("created_at")
    var created_at: String? = null

    @SerializedName("country_code")
    var country_code: String? = null
}