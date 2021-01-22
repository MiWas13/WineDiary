package ru.miwas.winediary.homelist.navigation

import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.record.RecordFragment

class HomeListNavigatorImpl(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : HomeListNavigator {

    override fun startAdding() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(CreateRecordFragment())
    }

    override fun openWineRecord(id: Long) {
        fragmentNavigationHelper.replaceFragmentWithBackStack(RecordFragment(id))
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}