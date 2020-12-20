package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_home_screen.*
import util.CommonUtils

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

        CommonUtils.saveUserLoginDetailInSharedPreferences(
            this.requireActivity(),
            args.userNameArgs
        )
        setWelcomeMessage(args.userNameArgs)

        btnLogout.setOnClickListener {
            CommonUtils.clearUserData(this.requireContext())
            launchLoginFragment()
        }
    }

    private fun setWelcomeMessage(userName: String) {
        val welcomeMessage: String = getString(R.string.welcome_user_name, userName)
        tvUsername.text = welcomeMessage
    }

    private fun launchLoginFragment() {
        findNavController().navigate(R.id.action_homeScreen_to_loginFragment)
    }

}
