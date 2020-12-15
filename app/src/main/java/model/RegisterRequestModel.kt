package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterRequestModel {
    @SerializedName("username")
    @Expose
    var userName: String? = null

    @SerializedName("firstname")
    @Expose
    var firstName: String? = null

    @SerializedName("lastname")
    @Expose
    var lastName: String? = null

    @SerializedName("phonenumber")
    @Expose
    var phoneNumber: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("country_code")
    @Expose
    var countryCode: String? = null
}