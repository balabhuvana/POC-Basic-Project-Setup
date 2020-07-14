package room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User?): Long

    @Query("SELECT * from user_table")
    fun getUserList(): LiveData<List<User>>

    @Query("SELECT * from user_table where user_name = :userName LIMIT 1")
    fun getUserRecord(userName: Int): LiveData<User>

    @Query("SELECT * from user_table where user_name = :userName LIMIT 1")
    fun getUserRecordTest(userName: Int): User

    @Query("SELECT * from user_table where user_name = :userName and user_password =:userPassword LIMIT 1")
    fun getUserRecordWithPassword(userName: Int, userPassword: String): LiveData<User>

}