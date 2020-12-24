package ru.miwas.winediary.main

import ru.miwas.winediary.base.BaseViewModel

interface MainViewModel : BaseViewModel<MainViewModel.Event> {

    sealed class Event {

    }
}