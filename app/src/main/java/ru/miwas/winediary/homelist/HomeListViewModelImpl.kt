package ru.miwas.winediary.homelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.base.App
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.homelist.model.WineItem
import ru.miwas.winediary.homelist.navigation.HomeListNavigator
import ru.miwas.winediary.homelist.HomeListViewModel.Event.AddClicked
import ru.miwas.winediary.homelist.HomeListViewModel.Event.WineClicked
import ru.miwas.winediary.utils.Constants.EMPTY_INT
import ru.miwas.winediary.utils.Constants.EMPTY_STRING

class HomeListViewModelImpl(
    private val homeListNavigator: HomeListNavigator
) : HomeListViewModel, ViewModel() {

    private val database: AppDatabase = App.instance.database

    override val wineItems: MutableLiveData<MutableList<WineItem>> = MutableLiveData()

    private val appMetricaSender: AppMetricaSender = App.instance.appMetricaSender

    override fun startProcesses() {
        appMetricaSender.sendEvent(EVENT_SHOW_HOME_LIST)
        fetchWineItemsData()
    }

    override fun dispatchEvent(event: HomeListViewModel.Event) {
        when (event) {
            AddClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_ADD)
                homeListNavigator.startAdding()
            }
            is WineClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_WINE_ITEM)
                homeListNavigator.openWineRecord(event.id)
            }
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
                it.rateTotal ?: EMPTY_INT
            )
        }.toMutableList()
    }

    companion object {
        const val EVENT_SHOW_HOME_LIST = "Home_list_screen_show"
        const val EVENT_CLICK_ADD = "Add_button_home_list_screen_click"
        const val EVENT_CLICK_WINE_ITEM = "Wine_item_home_list_screen_click"
    }
}