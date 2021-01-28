package ru.miwas.winediary.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.main.navigation.MainNavigator
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(
    private val mainNavigator: MainNavigator,
    private val database: AppDatabase
) : MainViewModel, ViewModel() {

    override fun startProcesses() {

        viewModelScope.launch {
            if (database.wineDao().getFirstWine().isNullOrEmpty()) {
                mainNavigator.openStubMainScreen()
            } else {
                mainNavigator.openHomeListScreen()
            }
        }
    }

    override fun dispatchEvent(event: MainViewModel.Event) {

    }

    override fun finishProcesses() {
        mainNavigator.clear()
    }
}