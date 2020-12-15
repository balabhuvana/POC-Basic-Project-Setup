package room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_maria_table")
class RegisterRequestRoomModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long = 0

    @SerializedName("data")
    @Expose
    @ColumnInfo(name = "user_name")
    var userName: String? = null

    @Expose
    @ColumnInfo(name = "user_password")
    var password: String? = null

    @Expose
    @ColumnInfo(name = "user_phone_number")
    var phoneNumber: String? = null

    @Expose
    @ColumnInfo(name = "user_first_name")
    var firstName: String? = null

    @Expose
    @ColumnInfo(name = "user_last_name")
    var lastName: String? = null

    @Expose
    @ColumnInfo(name = "user_country_code")
    var countryCode: String? = null

    @SerializedName("success")
    @Expose
    @ColumnInfo(name = "user_is_login")
    var isUserLogin: Boolean? = false

    @SerializedName("message")
    @Expose
    @ColumnInfo(name = "user_message")
    var message: String? = null

}