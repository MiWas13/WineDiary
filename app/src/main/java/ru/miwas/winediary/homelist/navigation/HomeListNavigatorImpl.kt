package ru.miwas.winediary.homelist.navigation

import ru.miwas.winediary.createrecord.CreateRecordFragment
import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import ru.miwas.winediary.record.RecordFragment
import ru.miwas.winediary.stubmain.StubMainFragment
import javax.inject.Inject

class HomeListNavigatorImpl @Inject constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : HomeListNavigator {

    override fun startAdding() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(CreateRecordFragment())
    }

    override fun showStub() {
        fragmentNavigationHelper.replaceFragmentWithBackStack(StubMainFragment())
    }

    override fun openWineRecord(id: Long) {
        fragmentNavigationHelper.replaceFragmentWithBackStack(RecordFragment(id))
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}