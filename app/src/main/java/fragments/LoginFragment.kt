package fragments


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.arunv.poc_basic_project_setup.R
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passwordEditTextOperation()

        next_button.setOnClickListener {
            tapOnNext()
        }
    }

    private fun isPasswordValid(@Nullable text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    private fun tapOnNext() {
        if (!isPasswordValid(password_edit_text.text)) {
            password_edit_text.error = getString(R.string.shr_error_password)
        } else {
            password_edit_text.error = null
        }
    }

    private fun passwordEditTextOperation() {
        password_edit_text.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(password_edit_text.text)) {
                password_edit_text.error = null //Clear the error
            }
            false
        }
    }

}
