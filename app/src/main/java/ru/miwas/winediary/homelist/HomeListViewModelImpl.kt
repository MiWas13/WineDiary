package ru.miwas.winediary.homelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.base.App
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.homelist.model.WineItem
import ru.miwas.winediary.homelist.navigation.HomeListNavigator

class HomeListViewModelImpl(
    private val homeListNavigator: HomeListNavigator
) : HomeListViewModel, ViewModel() {

    private val database: AppDatabase = App.instance.database

    override val wineItems: MutableLiveData<MutableList<WineItem>> = MutableLiveData()

    override fun startProcesses() {
        fetchWineItemsData()
    }

    override fun dispatchEvent(event: HomeListViewModel.Event) {

    }

    override fun finishProcesses() {
        homeListNavigator.clear()
    }

    private fun fetchWineItemsData() {
        viewModelScope.launch {
            wineItems.value = database.wineDao().getAllWinesLight().value?.toWineItems()
        }
    }

    private fun List<WineEntity>.toWineItems(): MutableList<WineItem> {
        return this.map {
            WineItem(
                it.id,
                it.name,
                it.imagePath,
                it.rateTotal
            )
        }.toMutableList()
    }
}