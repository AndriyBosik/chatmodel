package daniel.chatmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentSignInBinding
import daniel.chatmodel.model.Result
import daniel.chatmodel.viewmodel.AuthViewModel

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSignInBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.viewModel = viewModel

        viewModel.signInResult.observe(viewLifecycleOwner) { onSignInResult(it) }

        return binding.root
    }

    private fun onSignInResult(result: Result) {

        when (result) {
            Result.SUCCESS -> goToMain()
            Result.FAILURE -> signInFailed()
        }
    }

    private fun signInFailed() {
        Toast.makeText(activity, "Sign in failed", Toast.LENGTH_LONG).show()
    }

    private fun goToMain() {
        Toast.makeText(activity, "Successfully signed in", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_signInFragment_to_chatListFragment)
    }
}