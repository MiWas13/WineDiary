package ru.miwas.winediary.createrecord

import ru.miwas.winediary.createrecord.navigation.CreateRecordNavigator

class CreateRecordViewModelImpl(
    private val createRecordNavigator: CreateRecordNavigator
) : CreateRecordViewModel {

    override fun startProcesses() {

    }

    override fun dispatchEvent(event: CreateRecordViewModel.Event) {

    }

    override fun finishProcesses() {
        createRecordNavigator.clear()
    }
}