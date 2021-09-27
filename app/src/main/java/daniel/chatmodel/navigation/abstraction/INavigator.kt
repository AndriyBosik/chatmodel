package daniel.chatmodel.navigation.abstraction

import android.app.Activity
import androidx.fragment.app.Fragment
import daniel.chatmodel.model.Result

interface INavigator {

    fun configure(transitions: Map<Result, Pair<Int, INavigationHandler>>)

    fun navigate(activity: Activity, fragment: Fragment, destination: Result): Boolean
}