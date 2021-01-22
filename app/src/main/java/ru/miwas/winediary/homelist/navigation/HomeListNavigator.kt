package ru.miwas.winediary.homelist.navigation

import ru.miwas.winediary.base.BaseNavigator

interface HomeListNavigator : BaseNavigator {

    fun startAdding()

    fun openWineRecord(id: Long)
}