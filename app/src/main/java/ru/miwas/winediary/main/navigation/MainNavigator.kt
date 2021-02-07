package ru.miwas.winediary.main.navigation

import ru.miwas.winediary.core.base.BaseNavigator

interface MainNavigator : BaseNavigator {

    fun openStubMainScreen()

    fun openHomeListScreen()
}