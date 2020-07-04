package room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long = 0
        get() = field

    @ColumnInfo(name = "user_name")
    var userName: String = ""

    @ColumnInfo(name = "user_password")
    var password: String = ""

    @ColumnInfo(name = "user_is_logined")
    var isUserLogined: Boolean = false

}