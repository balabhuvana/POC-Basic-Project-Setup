package fragments


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_splash.*
import util.CommonUtils

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (validateUserIsLogined()) {
            launchHomeFragment()
        } else {
            launchLoginFragment()
        }
    }

    private fun launchLoginFragment() {

        showOrHideProgressBar(View.VISIBLE)
        Handler().postDelayed({
            val navDirections: NavDirections =
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            val navController: NavController = findNavController()
            navController.navigate(navDirections)
            showOrHideProgressBar(View.GONE)
        }, 2000)

    }

    private fun launchHomeFragment() {
        val username: String = CommonUtils.getUserName(this.requireContext())!!
        val homeNavDirections: NavDirections =
            SplashFragmentDirections.actionSplashFragmentToHomeFragment2(username.toInt())
        val navController: NavController = findNavController()
        navController.navigate(homeNavDirections)
    }

    private fun showOrHideProgressBar(showOrHide: Int) {
        splashProgressBar.visibility = showOrHide
    }

    private fun validateUserIsLogined(): Boolean {
        return CommonUtils.isUserLogined(this.activity!!)
    }

}
