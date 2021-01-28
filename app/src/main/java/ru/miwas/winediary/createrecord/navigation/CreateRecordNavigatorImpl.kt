package ru.miwas.winediary.createrecord.navigation

import ru.miwas.winediary.navigationcore.FragmentNavigationHelper
import javax.inject.Inject

class CreateRecordNavigatorImpl @Inject constructor(
    private val fragmentNavigationHelper: FragmentNavigationHelper
) : CreateRecordNavigator {

    override fun finish() {
        fragmentNavigationHelper.removeLastFragment()
    }

    override fun goToMainScreen() {
        fragmentNavigationHelper.goToMain()
    }

    override fun clear() {
        fragmentNavigationHelper.clearHelper()
    }
}