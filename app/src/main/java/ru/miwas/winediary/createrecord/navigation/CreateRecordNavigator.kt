package ru.miwas.winediary.createrecord.navigation

import ru.miwas.winediary.core.base.BaseNavigator

interface CreateRecordNavigator : BaseNavigator {

    fun finish()

    fun goToMainScreen()
}