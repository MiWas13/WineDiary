package ru.miwas.winediary.stubmain.navigation

import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class StubMainNavigatorImpl @Inject constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : StubMainNavigator {

    override fun startAdding() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(CreateRecordFragment())
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}