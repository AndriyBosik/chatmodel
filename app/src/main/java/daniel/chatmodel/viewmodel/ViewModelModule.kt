package daniel.chatmodel.viewmodel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import daniel.chatmodel.navigation.abstraction.INavigator
import daniel.chatmodel.navigation.implementation.Navigator

@Module
@InstallIn(FragmentComponent::class)
interface ViewModelModule {

    @Binds
    fun bindNavigator(navigator: Navigator): INavigator
}