package fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.arunv.poc_basic_project_setup.R

/**
 * A simple [Fragment] subclass.
 */
class HomeScreen : Fragment() {


    private val args: HomeScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWelcomeMessage(args.userNameArgs)
    }

    private fun setWelcomeMessage(userName: String) {
        val welcomeMessage: String = getString(R.string.welcome_user_name, userName.toString())
        Log.i("----> ", "welcomeMessage:$welcomeMessage")
    }


}
