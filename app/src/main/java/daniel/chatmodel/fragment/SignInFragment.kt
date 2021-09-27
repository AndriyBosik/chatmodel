package daniel.chatmodel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import daniel.chatmodel.R
import daniel.chatmodel.databinding.FragmentSignInBinding
import daniel.chatmodel.model.Result
import daniel.chatmodel.navigation.abstraction.INavigator
import daniel.chatmodel.navigation.implementation.SignInFailureNavigationHandler
import daniel.chatmodel.navigation.implementation.SignInSuccessNavigationHandler
import daniel.chatmodel.viewmodel.AuthViewModel
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    @Inject lateinit var navigator: INavigator
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSignInBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.viewModel = viewModel

        navigator.configure(hashMapOf(
            Result.SUCCESS to Pair(R.id.action_signInFragment_to_chatListFragment, SignInSuccessNavigationHandler()),
            Result.FAILURE to Pair(0, SignInFailureNavigationHandler())
        ))
        viewModel.signInResult.observe(viewLifecycleOwner) {
            navigator.navigate(requireActivity(), this, it)
        }

        return binding.root
    }
}