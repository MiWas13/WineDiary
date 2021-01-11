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
import ru.miwas.winediary.homelist.HomeListViewModel.Event.AddClicked
import ru.miwas.winediary.utils.Constants.EMPTY_STRING

class HomeListViewModelImpl(
    private val homeListNavigator: HomeListNavigator
) : HomeListViewModel, ViewModel() {

    private val database: AppDatabase = App.instance.database

    override val wineItems: MutableLiveData<MutableList<WineItem>> = MutableLiveData()

    override fun startProcesses() {
        fetchWineItemsData()
    }

    override fun dispatchEvent(event: HomeListViewModel.Event) {
        when (event) {
            AddClicked -> homeListNavigator.startAdding()
        }
    }

    override fun finishProcesses() {
        homeListNavigator.clear()
    }

    private fun fetchWineItemsData() {
        viewModelScope.launch {
            wineItems.value = database.wineDao().getAllWinesLight().toWineItems()
        }
    }

    private fun List<WineEntity>.toWineItems(): MutableList<WineItem> {
        return this.map {
            WineItem(
                it.id,
                it.name ?: EMPTY_STRING,
                it.imagePath,
                it.rateTotal ?: 0
            )
        }.toMutableList()
    }
}