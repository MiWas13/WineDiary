package ru.miwas.winediary.record

import ru.miwas.winediary.record.navigation.RecordNavigator

class RecordViewModelImpl(
    private val recordNavigator: RecordNavigator
) : RecordViewModel {

    override fun startProcesses() {

    }

    override fun dispatchEvent(event: RecordViewModel.Event) {

    }

    override fun finishProcesses() {
        recordNavigator.clear()
    }
}