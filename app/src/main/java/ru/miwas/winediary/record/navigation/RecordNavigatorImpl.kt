package ru.miwas.winediary.record.navigation

import ru.miwas.winediary.navigationcore.FragmentNavigationHelper

class RecordNavigatorImpl(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : RecordNavigator {

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}