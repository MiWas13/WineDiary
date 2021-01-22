package ru.miwas.winediary.homelist

import androidx.lifecycle.MutableLiveData
import ru.miwas.winediary.base.BaseViewModel
import ru.miwas.winediary.homelist.model.WineItem

interface HomeListViewModel : BaseViewModel<HomeListViewModel.Event> {

    val wineItems: MutableLiveData<MutableList<WineItem>>

    sealed class Event {
        object AddClicked : Event()
        class WineClicked(val id: Long) : Event()
    }
}