package dagger

import android.app.Application
import fragments.LoginFragment
import fragments.RegistrationFragment
import module.AppModule
import module.RoomModule
import room.PatientDao
import room.UserDao
import room.UserRoomDatabase
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment?)
    fun inject(registrationFragment: RegistrationFragment?)
}