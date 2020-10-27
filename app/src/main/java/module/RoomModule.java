package module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import room.PatientDao;
import room.UserDao;
import room.UserRoomDatabase;

@Module
public class RoomModule {

    private UserRoomDatabase userRoomDatabase;

    public RoomModule(Application mApplication) {
        userRoomDatabase = Room.databaseBuilder(mApplication, UserRoomDatabase.class, "demo-db").build();
    }

    @Singleton
    @Provides
    UserRoomDatabase providesUserRoomDatabase() {
        return userRoomDatabase;
    }

    @Singleton
    @Provides
    UserDao providesUserDao(UserRoomDatabase userRoomDatabase) {
        return userRoomDatabase.userDao();
    }

    @Singleton
    @Provides
    PatientDao providesPatientDao(UserRoomDatabase userRoomDatabase) {
        return userRoomDatabase.patientDao();
    }

}
