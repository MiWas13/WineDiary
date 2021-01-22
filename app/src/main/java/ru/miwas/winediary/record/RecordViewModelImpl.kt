package ru.miwas.winediary.record

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miwas.winediary.base.App
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.record.model.Wine
import ru.miwas.winediary.record.navigation.RecordNavigator
import ru.miwas.winediary.utils.Constants.EMPTY_FLOAT
import ru.miwas.winediary.utils.Constants.EMPTY_INT
import ru.miwas.winediary.utils.Constants.EMPTY_STRING

class RecordViewModelImpl(
    private val recordNavigator: RecordNavigator
) : RecordViewModel, ViewModel() {

    private val database: AppDatabase = App.instance.database

    override val wine: MutableLiveData<Wine> = MutableLiveData()
    private var wineId: Long = 0

    override fun startProcesses() {
        viewModelScope.launch {
            wine.value = database.wineDao().getWineById(wineId).toWine()
        }
    }

    override fun dispatchEvent(event: RecordViewModel.Event) {}

    override fun finishProcesses() {
        recordNavigator.clear()
    }

    private fun WineEntity.toWine(): Wine {
        return this.let {
            Wine(
                it.name ?: EMPTY_STRING,
                it.country ?: EMPTY_STRING,
                it.year ?: EMPTY_INT,
                it.alcoholPercentage ?: EMPTY_FLOAT,
                it.color ?: EMPTY_STRING,
                it.price ?: EMPTY_INT,
                it.imagePath ?: EMPTY_STRING,
                it.grapeVariety ?: EMPTY_STRING,
                it.smell ?: EMPTY_STRING,
                it.taste ?: EMPTY_STRING,
                it.combination ?: EMPTY_STRING,
                it.notes ?: EMPTY_STRING,
                it.rateSmell ?: EMPTY_INT,
                it.rateTaste ?: EMPTY_INT,
                it.rateTotal ?: EMPTY_INT
            )
        }
    }


    override fun setWineId(id: Long) {
        wineId = id
    }
}