package dagger

import android.app.Application
import androidx.room.Room
import room.PatientDao
import room.UserDao
import room.UserRoomDatabase
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application) {
    private val userRoomDatabase: UserRoomDatabase

    @Singleton
    @Provides
    fun providesUserRoomDatabase(): UserRoomDatabase {
        return userRoomDatabase
    }

    @Singleton
    @Provides
    fun providesUserDao(userRoomDatabase: UserRoomDatabase): UserDao {
        return userRoomDatabase.userDao()
    }

    @Singleton
    @Provides
    fun providesPatientDao(userRoomDatabase: UserRoomDatabase): PatientDao {
        return userRoomDatabase.patientDao()
    }

    init {
        userRoomDatabase = Room.databaseBuilder(
            mApplication!!,
            UserRoomDatabase::class.java, "demo-db"
        ).build()
    }
}
