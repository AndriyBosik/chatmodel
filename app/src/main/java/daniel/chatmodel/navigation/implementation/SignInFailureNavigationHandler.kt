package daniel.chatmodel.navigation.implementation

import android.content.Context
import android.widget.Toast

class SignInFailureNavigationHandler: DefaultNavigationHandler() {

    override fun onBeforeNavigate(context: Context): Boolean {
        Toast.makeText(context, "Sign in failed", Toast.LENGTH_LONG).show()
        return false
    }
}