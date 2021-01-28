package ru.miwas.winediary.record.navigation

import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class RecordNavigatorImpl @Inject constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : RecordNavigator {

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}