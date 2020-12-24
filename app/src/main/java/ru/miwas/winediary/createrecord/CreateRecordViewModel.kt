package ru.miwas.winediary.createrecord

import ru.miwas.winediary.base.BaseViewModel

interface CreateRecordViewModel : BaseViewModel<CreateRecordViewModel.Event> {

    sealed class Event {

        object NextStepClicked : Event()

        object ConfirmClicked : Event()
    }
}