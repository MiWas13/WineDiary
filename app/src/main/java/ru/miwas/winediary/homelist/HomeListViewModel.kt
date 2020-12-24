package ru.miwas.winediary.homelist

import ru.miwas.winediary.base.BaseViewModel

interface HomeListViewModel : BaseViewModel<HomeListViewModel.Event> {

    sealed class Event {

    }
}