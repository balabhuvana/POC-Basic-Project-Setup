package dagger

import fragments.LoginFragment
import fragments.RegistrationFragment
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment?)
    fun inject(registrationFragment: RegistrationFragment?)
}