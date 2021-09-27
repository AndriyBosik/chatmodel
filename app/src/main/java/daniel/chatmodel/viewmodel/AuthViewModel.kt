package daniel.chatmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import daniel.chatmodel.model.Result
import daniel.chatmodel.livedata.UserCredentials
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {
    private val auth = Firebase.auth

    private val _signInResult = MutableLiveData<Result>()
    val signInResult: LiveData<Result> = _signInResult
    val credentials = MutableLiveData(UserCredentials())

    init {
        if (auth.currentUser != null) {
            _signInResult.value = Result.SUCCESS
        }
    }

    fun signIn() {
        /*auth.signInWithEmailAndPassword(credentials.value!!.email.value!!, credentials.value!!.password.value!!)
            .addOnCompleteListener { onSignInResult(it) }*/
        auth.signInWithEmailAndPassword("aaaa@fd.com", "12345678")
            .addOnCompleteListener { onSignInResult(it) }
    }

    private fun onSignInResult(task: Task<AuthResult>) {
        _signInResult.value = if (task.isSuccessful) Result.SUCCESS else Result.FAILURE
    }
}