package daniel.chatmodel.navigation.implementation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import daniel.chatmodel.model.Result
import daniel.chatmodel.navigation.abstraction.INavigator
import daniel.chatmodel.navigation.abstraction.INavigationHandler
import javax.inject.Inject

class Navigator @Inject constructor(): INavigator {

    private lateinit var transitions: Map<Result, Pair<Int, INavigationHandler>>

    override fun configure(transitions: Map<Result, Pair<Int, INavigationHandler>>) {
        this.transitions = transitions
    }

    override fun navigate(activity: Activity, fragment: Fragment, destination: Result): Boolean {
        val pair = transitions[destination]
        if (pair!!.second.onBeforeNavigate(activity)) {
            fragment.findNavController().navigate(pair.first)
            pair.second.onAfterNavigate(activity)
            return true
        }
        return false
    }
}