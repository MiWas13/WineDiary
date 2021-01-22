package ru.miwas.winediary.stubmain

import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.base.App
import ru.miwas.winediary.stubmain.navigation.StubMainNavigator

class StubMainViewModelImpl(
    private val stubMainNavigator: StubMainNavigator
) : StubMainViewModel {

    private val appMetricaSender: AppMetricaSender = App.instance.appMetricaSender

    override fun startProcesses() {
        appMetricaSender.sendEvent(EVENT_SHOW_STUB_MAIN)
    }

    override fun dispatchEvent(event: StubMainViewModel.Event) {
        when (event) {
            StubMainViewModel.Event.AddClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_ADD)
                stubMainNavigator.startAdding()
            }
        }
    }

    override fun finishProcesses() {
        stubMainNavigator.clear()
    }

    companion object {
        const val EVENT_SHOW_STUB_MAIN = "Stub_main_screen_show"
        const val EVENT_CLICK_ADD = "Add_button_stub_main_screen_click"
    }
}