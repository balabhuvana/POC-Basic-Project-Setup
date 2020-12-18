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

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (validateUserIsLogin()) {
            launchHomeFragment()
        } else {
            launchLoginFragment()
        }
    }

    private fun launchLoginFragment() {

        showOrHideProgressBar(View.VISIBLE)
        Handler().postDelayed({
            val navDirections: NavDirections =
                SplashFragmentDirections.actionSplashToLoginFragment()
            val navController: NavController = findNavController()
            navController.navigate(navDirections)
            showOrHideProgressBar(View.GONE)
        }, 2000)

    }

    private fun showOrHideProgressBar(showOrHide: Int) {
        splashProgressBar.visibility = showOrHide
    }

    private fun validateUserIsLogin(): Boolean {
        return CommonUtils.isUserLogined(this.activity!!)
    }

    private fun launchHomeFragment() {
        val username: String = CommonUtils.getUserName(this.requireContext())!!
        SplashFragmentDirections.actionSplashToLoginFragment()
        val homeNavDirections: NavDirections =
            SplashFragmentDirections.actionSplashFragmentToHomeScreen(username)
        val navController: NavController = findNavController()
        navController.navigate(homeNavDirections)
    }

}
