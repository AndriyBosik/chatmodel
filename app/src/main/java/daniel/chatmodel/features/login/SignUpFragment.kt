package daniel.chatmodel.features.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import daniel.chatmodel.R
import daniel.chatmodel.model.Result
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_sign_up.setOnClickListener { onSignUpClicked() }
        viewModel.signUpResult.observe(viewLifecycleOwner) { onSignUpResult(it) }
    }

    private fun onSignUpResult(result: Result) {
        when (result) {
            Result.SUCCESS -> goToMain()
            Result.FAILURE -> signUpFailed()
        }
    }

    private fun signUpFailed() {
        Toast.makeText(activity, "Sign up failed", Toast.LENGTH_LONG).show()
    }

    private fun onSignUpClicked() {
        val email = et_sign_up_email.text.toString()
        val password = et_sign_up_password.text.toString()
        viewModel.signUp(email, password)
    }

    private fun goToMain() {
        Toast.makeText(activity, "Successfully created the account", Toast.LENGTH_LONG).show()
    }
}