package daniel.chatmodel.navigation.abstraction

import android.content.Context

interface INavigationHandler {

    fun onBeforeNavigate(context: Context): Boolean

    fun onAfterNavigate(context: Context)
}