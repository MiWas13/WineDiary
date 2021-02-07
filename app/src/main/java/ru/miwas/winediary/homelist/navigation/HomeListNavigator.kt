package ru.miwas.winediary.homelist.navigation

import ru.miwas.winediary.core.base.BaseNavigator

interface HomeListNavigator : BaseNavigator {

    fun startAdding()

    fun openWineRecord(id: Long)
}