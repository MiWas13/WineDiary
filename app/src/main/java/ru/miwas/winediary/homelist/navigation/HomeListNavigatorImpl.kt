package ru.miwas.winediary.homelist.navigation

import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper

class HomeListNavigatorImpl(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : HomeListNavigator {

    override fun startAdding() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(CreateRecordFragment())
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}