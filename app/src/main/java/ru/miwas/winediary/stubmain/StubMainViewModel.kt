package ru.miwas.winediary.stubmain

import ru.miwas.winediary.base.BaseViewModel

interface StubMainViewModel: BaseViewModel<StubMainViewModel.Event> {

    sealed class Event {
        object AddClicked : Event()
    }
}