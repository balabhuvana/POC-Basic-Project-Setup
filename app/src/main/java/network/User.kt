package network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    @SerializedName("id")
    var userId: Int = 0,

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var userFirstName: String = "",

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    var userLastName: String = "",

    @ColumnInfo(name = "user_email")
    @SerializedName("email")
    var userEmail: String? = null,

    @ColumnInfo(name = "user_avatar")
    @SerializedName("avatar")
    var userAvatar: String = ""
)