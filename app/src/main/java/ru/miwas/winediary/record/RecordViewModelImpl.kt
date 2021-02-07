package ru.miwas.winediary.record

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.miwas.winediary.appmetrica.AppMetricaSender
import ru.miwas.winediary.database.AppDatabase
import ru.miwas.winediary.database.model.WineEntity
import ru.miwas.winediary.record.model.Wine
import ru.miwas.winediary.record.RecordViewModel.Event.BackButtonClicked
import ru.miwas.winediary.record.RecordViewModel.Event.DeleteButtonClicked
import ru.miwas.winediary.record.RecordViewModel.Event.DeleteConfirmationButtonClicked
import ru.miwas.winediary.record.navigation.RecordNavigator
import ru.miwas.winediary.utils.Constants.EMPTY_FLOAT
import ru.miwas.winediary.utils.Constants.EMPTY_INT
import ru.miwas.winediary.utils.Constants.EMPTY_STRING
import javax.inject.Inject

class RecordViewModelImpl @Inject constructor(
    private val recordNavigator: RecordNavigator,
    private val database: AppDatabase,
    private val appMetricaSender: AppMetricaSender
) : RecordViewModel, ViewModel() {

    override val wine: MutableLiveData<Wine> = MutableLiveData()
    override val deleteConfirmationDialogState: MutableLiveData<Boolean> = MutableLiveData()

    private var wineId: Long = 0

    override fun startProcesses() {
        appMetricaSender.sendEvent(EVENT_SHOW_RECORD)
        viewModelScope.launch {
            wine.value = database.wineDao().getWineById(wineId).toWine()
        }
    }

    override fun dispatchEvent(event: RecordViewModel.Event) {
        when (event) {
            BackButtonClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_BACK)
                recordNavigator.back()
            }
            DeleteButtonClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_DELETE)
                showDeleteConfirmationDialog()
            }
            DeleteConfirmationButtonClicked -> {
                appMetricaSender.sendEvent(EVENT_CLICK_DELETE_CONFIRMATION)
                deleteItem()
            }
        }

    }

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

    private fun showDeleteConfirmationDialog() {
        deleteConfirmationDialogState.value = true
    }

    private fun deleteItem() {
        runBlocking {
            val deleteDeferred = async {
                database.wineDao().delete(wineId)
            }
            deleteDeferred.await()
            recordNavigator.back()
        }
    }

    override fun setWineId(id: Long) {
        wineId = id
    }

    companion object {
        const val EVENT_SHOW_RECORD = "Record_screen_show"
        const val EVENT_CLICK_BACK = "Back_button_record_screen_click"
        const val EVENT_CLICK_DELETE = "Delete_button_record_screen_click"
        const val EVENT_CLICK_DELETE_CONFIRMATION = "Delete_confirm_button_record_screen_click"

    }
}