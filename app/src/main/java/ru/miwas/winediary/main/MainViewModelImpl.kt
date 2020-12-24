package ru.miwas.winediary.main

import ru.miwas.winediary.main.navigation.MainNavigator

class MainViewModelImpl(
    private val mainNavigator: MainNavigator
) : MainViewModel {

    var hasAnyRecords = false

    override fun startProcesses() {
        if (hasAnyRecords) {
            mainNavigator.openHomeListScreen()
        } else {
            mainNavigator.openStubMainScreen()
        }
    }

    override fun dispatchEvent(event: MainViewModel.Event) {

    }

    override fun finishProcesses() {
        mainNavigator.clear()
    }
}