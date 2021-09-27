package daniel.chatmodel.navigation.implementation

import android.content.Context
import android.widget.Toast

class SignInSuccessNavigationHandler: DefaultNavigationHandler() {

    override fun onBeforeNavigate(context: Context): Boolean {
        Toast.makeText(context, "Successfully signed in", Toast.LENGTH_LONG).show()
        return true
    }
}