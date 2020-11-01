package network

import com.google.gson.annotations.SerializedName

class UserListModelRoot {
    @SerializedName("page")
    var page: Int = 2

    @SerializedName("per_page")
    var perPage: Int = 0

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("total_pages")
    var totaPages: Int = 0

    @SerializedName("data")
    var userModelList: List<User>? = null
}