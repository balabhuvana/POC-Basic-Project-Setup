package room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RegisterRequestRoomModel::class],
    version = 1,
    exportSchema = false
)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}