package ru.miwas.winediary.record

import ru.miwas.winediary.base.BaseViewModel

interface RecordViewModel : BaseViewModel<RecordViewModel.Event> {

    sealed class Event {

    }
}