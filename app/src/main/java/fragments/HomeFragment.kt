package fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_home.*
import util.CommonUtils

class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        CommonUtils.saveUserLoginDetailInSharedPreferences(
            this.activity!!,
            args.userNameArgs.toString()
        )

        setWelcomeMessage(args.userNameArgs)

        btnStartNextScreen.setOnClickListener {
            launchSecondScreen()
        }
    }

    private fun setWelcomeMessage(userName: Int) {
        val welcomeMessage: String = getString(R.string.welcome_user_name, userName.toString())
        tvUserName.text = welcomeMessage
    }

    private fun launchSecondScreen() {
        val bundle: Bundle = bundleOf("amount" to " World ")
        findNavController().navigate(R.id.action_homeFragment_to_secondFragment, bundle)
    }

}
