package ru.miwas.winediary.main

import ru.miwas.winediary.base.App
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.main.navigation.MainNavigator

class MainViewModelImpl(
    private val mainNavigator: MainNavigator
) : MainViewModel {

    override fun startProcesses() {
        val database: AppDatabase = App.instance.database

        if (database.wineDao().getFirstWine().isNullOrEmpty()) {
            mainNavigator.openStubMainScreen()
        } else {
            mainNavigator.openHomeListScreen()
        }
    }

    override fun dispatchEvent(event: MainViewModel.Event) {

    }

    override fun finishProcesses() {
        mainNavigator.clear()
    }
}