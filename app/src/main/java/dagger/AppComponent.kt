package dagger

import android.app.Application
import fragments.LoginFragment
import fragments.RegistrationFragment

@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance application: Application): AppComponent
    }

    // Classes that can be injected by this Component
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(loginFragment: LoginFragment)
}