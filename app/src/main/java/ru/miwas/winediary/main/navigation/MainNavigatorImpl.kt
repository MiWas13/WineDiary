package ru.miwas.winediary.main.navigation

import ru.miwas.winediary.homelist.HomeListFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.stubmain.StubMainFragment
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : MainNavigator {

    override fun openStubMainScreen() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(StubMainFragment())
    }

    override fun openHomeListScreen() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(HomeListFragment())
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}