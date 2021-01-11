package ru.miwas.winediary.createrecord.navigation

import ru.miwas.winediary.navigationcore.FragmentNavigationHelper

class CreateRecordNavigatorImpl(
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