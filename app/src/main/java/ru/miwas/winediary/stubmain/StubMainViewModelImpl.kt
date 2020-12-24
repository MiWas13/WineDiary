package ru.miwas.winediary.stubmain

import ru.miwas.winediary.stubmain.navigation.StubMainNavigator

class StubMainViewModelImpl(
    private val stubMainNavigator: StubMainNavigator
) : StubMainViewModel {

    override fun startProcesses() {

    }

    override fun dispatchEvent(event: StubMainViewModel.Event) {
        when (event) {
            StubMainViewModel.Event.AddClicked -> stubMainNavigator.startAdding()
        }
    }

    override fun finishProcesses() {
        stubMainNavigator.clear()
    }
}