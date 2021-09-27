package daniel.chatmodel.navigation.implementation

import android.content.Context
import daniel.chatmodel.navigation.abstraction.INavigationHandler

open class DefaultNavigationHandler: INavigationHandler {
    override fun onBeforeNavigate(context: Context): Boolean = true

    override fun onAfterNavigate(context: Context) {}
}