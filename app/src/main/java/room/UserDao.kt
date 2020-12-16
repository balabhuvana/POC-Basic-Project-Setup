package room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserMariaData(registerRequestRoomModel: RegisterRequestRoomModel?): Long

    @Query("SELECT * from user_maria_table where user_name = :userName LIMIT 1")
    fun getUserRecordMaria(userName: String): LiveData<RegisterRequestRoomModel>

    @Query("SELECT * from user_maria_table where user_name = :userName and user_password =:userPassword LIMIT 1")
    fun getUserRecordMariaWithPassword(
        userName: String,
        userPassword: String
    ): LiveData<RegisterRequestRoomModel>

    @Query("SELECT * from user_maria_table")
    fun getUserListLogin(): LiveData<List<RegisterRequestRoomModel>>

}