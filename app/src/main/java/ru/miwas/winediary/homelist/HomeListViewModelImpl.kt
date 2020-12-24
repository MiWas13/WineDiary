package ru.miwas.winediary.homelist

import ru.miwas.winediary.homelist.navigation.HomeListNavigator

class HomeListViewModelImpl(
    private val homeListNavigator: HomeListNavigator
) : HomeListViewModel {

    override fun startProcesses() {

    }

    override fun dispatchEvent(event: HomeListViewModel.Event) {

    }

    override fun finishProcesses() {
        homeListNavigator.clear()
    }
}